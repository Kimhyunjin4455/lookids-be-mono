package lookids.auth.auth.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.auth.auth.dto.in.PostUserRequestDto;
import lookids.auth.auth.dto.in.SignUpRequestDto;
import lookids.auth.auth.repository.AuthRepository;
import lookids.auth.common.entity.BaseResponse;
import lookids.auth.common.entity.BaseResponseStatus;
import lookids.auth.common.exception.BaseException;
import lookids.auth.common.utils.UuidGenerator;

@Slf4j
@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {

	private final AuthRepository authRepository;
	private final UserServiceClient userServiceClient;
	private final PasswordEncoder passwordEncoder;
	private final RedisTemplate<String, String> redisTemplate;
	private final int TRY_CNT_LIMIT = 3;

	// Todo: 무조건 하나의 과정으로 다 끝내기보단 회원가입 처리를 먼저 끝내고 redis등을 이용해 uuid, nickname을 저장한 후 로그인 시 외부 api에 값 전달 의견
	@Override
	@Transactional // 회원가입 시 유저db에 값 저장 필요
	public void signUp(SignUpRequestDto loginRequestDto) {
		log.info("사용자 가입 시도: 로그인 ID = {}", loginRequestDto.getLoginId());

		String createdUuid = UuidGenerator.generateNormalUuid();

		// 여러 스레드가 동시에 signUp 메서드를 호출할 경우, Race Condition이 발생할 수 있음
		// @Transactional 사용 시, 중복 UUID가 발생할 경우 전체 작업이 롤백되므로 데이터의 일관성을 유지할 수 있지만 회원가입 시 튕기는 현상또한 옳지 않다고 판단
		// Todo: while을 통한 방법이 마냥 좋은 것 같지 않다 -> 3회 정도로 제한
		for (int tryCnt = 0; authRepository.existsByUuid(createdUuid) && tryCnt < TRY_CNT_LIMIT; tryCnt++) {
			createdUuid = UuidGenerator.generateNormalUuid();
		}

		// 시도 횟수가 최대치를 초과한 경우 예외 발생
		if (authRepository.existsByUuid(createdUuid)) {
			throw new BaseException(BaseResponseStatus.ATTEMPT_LIMIT_EXCEEDED);
		}


		// 개선안 -> 닉네임 입력시 redis에 먼저 그 값을 저장하는 방식으로 동시성 처리(중복 방지) 가능
		// 외부 api에 값 전달
		writeUserProfile(createdUuid, loginRequestDto.getNickname());

		authRepository.save(loginRequestDto.toEntity(passwordEncoder, createdUuid));

		// 회원가입 후 redis에서 아이디 삭제
		redisTemplate.delete(loginRequestDto.getLoginId());
	}



	// User 서비스에 전달할 데이터 생성
	public void writeUserProfile(String uuid, String nickname) {
		try {
			// 요청 바디 생성
			PostUserRequestDto requestDto = PostUserRequestDto.toDto(uuid, nickname);

			BaseResponse<Void> response = userServiceClient.writeUserProfile(requestDto);

			log.info("API URL: {}", response);

			if (response.httpStatus() == HttpStatus.OK) {
				log.info("외부 API 호출 성공 - UUID: {}, Nickname: {}", uuid, nickname);
			} else {
				log.error("외부 API 호출 실패 - status: {}", response.httpStatus());
				throw new BaseException(BaseResponseStatus.EXTERNAL_API_ERROR);
			}

		} catch (FeignException e) {
			// FeignException에서 상태 코드와 메시지를 로그에 추가
			log.error("FeignException 발생: 상태 코드: {}, 메시지: {}", e.status(), e.getMessage());
			throw new BaseException(BaseResponseStatus.EXTERNAL_API_ERROR);
		} catch (RestClientException e) {
			log.error("외부 API 호출 중 예외 발생", e);
			throw new BaseException(BaseResponseStatus.EXTERNAL_API_ERROR);
		}

	}
}
