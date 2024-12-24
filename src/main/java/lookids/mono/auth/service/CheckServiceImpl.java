package lookids.mono.auth.service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.auth.domain.Auth;
import lookids.mono.auth.dto.in.EmailVerifyRequestDto;
import lookids.mono.auth.dto.in.KeyRequestDto;
import lookids.mono.auth.dto.in.PasswordRequestDto;
import lookids.mono.auth.dto.out.KeyResponseDto;
import lookids.mono.auth.dto.out.LoginIdFindResponseDto;
import lookids.mono.auth.repository.AuthRepository;
import lookids.mono.common.entity.BaseResponseStatus;
import lookids.mono.common.exception.BaseException;

@Slf4j
@RequiredArgsConstructor
@Service
public class CheckServiceImpl implements CheckService {

	private final PasswordEncoder passwordEncoder;
	private final AuthRepository authRepository;
	// redis
	private final RedisTemplate<String, String> redisTemplate;

	@Override
	public KeyResponseDto verifyPassword(PasswordRequestDto passwordRequestDto) {

		// 입력받은 ketRequestDto의 getKey 값인 password 통해 uuid 를 추출하고
		// getKey 인코딩 한것과 uuid를 통해 authRepository의 암호화된 패스워드와 비교
		// 비밀번호가 일치하면 true, 불일치하면 false

		Optional<Auth> auth = authRepository.findByUuid(passwordRequestDto.getUuid());

		if (auth.isPresent() && passwordEncoder.matches(passwordRequestDto.getPassword(), auth.get().getPassword())) {
			log.info(passwordRequestDto.getPassword());
			log.info(auth.get().getPassword());
			return KeyResponseDto.builder().verification(true).build();
		} else {
			throw new BaseException(BaseResponseStatus.PASSWORD_MATCH_FAILED);
		}
	}

	@Override
	public KeyResponseDto verifyEmail(KeyRequestDto keyRequestDto) {
		Optional<Auth> user = authRepository.findByUserEmail(keyRequestDto.getKey());
		if (user.isPresent()) {
			return KeyResponseDto.builder().verification(false).build();
		}
		return KeyResponseDto.builder().verification(true).build();
	}

	@Override
	public KeyResponseDto verifyLoginId(KeyRequestDto keyRequestDto) {
		Optional<Auth> user = authRepository.findByLoginId(keyRequestDto.getKey());

		if (user.isPresent()) {
			throw new BaseException(BaseResponseStatus.DUPLICATED_USER);
		} else {
			// 저장 및 조회를 위해 id 자체를 key로 사용
			if (redisTemplate.opsForValue().get(keyRequestDto.getKey()) != null) {
				throw new BaseException(BaseResponseStatus.ALREADY_USED_ID);
			}
			// 임시로 메모리에 저장 -> 회원가입 완료 시 저장
			redisTemplate.opsForValue().set(keyRequestDto.getKey(), keyRequestDto.getKey(), 30, TimeUnit.MINUTES);

			return KeyResponseDto.builder().verification(true).build();
		}
	}

	@Override
	public KeyResponseDto checkVerificationCode(EmailVerifyRequestDto emailVerifyRequestDto) {
		// emailVerifyRequestDto의 getEmail()로 redis에 저장된 인증코드를 가져온다.
		// 인증코드가 일치하면 return 다르면 오류 발생
		String key = getEmailVerificationCode(emailVerifyRequestDto.getEmail());

		log.info("code: {}", emailVerifyRequestDto.getKey());
		log.info("key: {}", key);
		if (key == null || !key.equals(emailVerifyRequestDto.getKey())) {
			throw new BaseException(BaseResponseStatus.NO_EXIST_USER);
		}
		return KeyResponseDto.builder().verification(true).build();

	}

	// 이메일 인증 코드 조회
	public String getEmailVerificationCode(String email) {
		Object code = redisTemplate.opsForValue().get("EmailAuth:" + email);
		return code != null ? code.toString() : null;
	}

	@Override
	// 이메일을 통해(이메일 본인인증 -> 프론트에서 이메일값 통해 -> 아이디 찾기
	public LoginIdFindResponseDto findIdByEmail(KeyRequestDto keyRequestDto) {
		Optional<Auth> user = authRepository.findByUserEmail(keyRequestDto.getKey());
		log.info("email: " + keyRequestDto.getKey());
		if (user.isPresent()) {
			log.info("id: " + user.get().getLoginId());
			return LoginIdFindResponseDto.builder().loginId(user.get().getLoginId()).build();
		}
		throw new BaseException(BaseResponseStatus.NO_EXIST_USER);

	}

	@Override
	public void putPassword(PasswordRequestDto passwordRequestDto) {
		Auth auth = authRepository.findByUuid(passwordRequestDto.getUuid())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));

		Auth updatedAuth = Auth.builder()
			.id(auth.getId())
			.uuid(auth.getUuid())
			.password(passwordEncoder.encode(passwordRequestDto.getPassword()))
			.loginId(auth.getLoginId())
			.userEmail(auth.getUserEmail())
			.build();

		authRepository.save(updatedAuth);
	}
}
