package lookids.auth.auth.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.auth.auth.domain.Auth;
import lookids.auth.auth.domain.OAuth;
import lookids.auth.auth.dto.in.OAuthSignUpRequestDto;
import lookids.auth.auth.dto.in.PostUserRequestDto;
import lookids.auth.auth.dto.in.RefreshTokenRequestDto;
import lookids.auth.auth.dto.in.SignInRequestDto;
import lookids.auth.auth.dto.out.RefreshTokenResponseDto;
import lookids.auth.auth.dto.out.SignInResponseDto;
import lookids.auth.auth.repository.AuthRepository;
import lookids.auth.auth.repository.OAuthRepository;
import lookids.auth.auth.userdetails.AuthUserDetails;
import lookids.auth.common.config.RefreshToken;
import lookids.auth.common.entity.BaseResponse;
import lookids.auth.common.entity.BaseResponseStatus;
import lookids.auth.common.entity.OAuthAuthenticationToken;
import lookids.auth.common.exception.BaseException;
import lookids.auth.common.jwt.JwtTokenProvider;
import lookids.auth.common.utils.UuidGenerator;

@Slf4j
@RequiredArgsConstructor
@Service
public class SignInServiceImpl implements SignInService {

	private final AuthRepository authRepository;
	private final OAuthRepository oAuthRepository;

	//로그인 시 필요한 repository
	private final JwtTokenProvider jwtTokenProvider;
	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;

	// redis
	private final RedisTemplate<String, String> redisTemplate;

	// User 서비스에 전달할 데이터 생성, 스파로스 측 와이파이 연결 필요,,,
	private final UserServiceClient userServiceClient;


	@Override
	public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {
		// 1. Id로 사용자 찾기
		Auth auth = authRepository.findByLoginId(signInRequestDto.getLoginId())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.FAILED_TO_LOGIN));

		// 2. 사용자 인증 (비밀번호 검증)
		authenticate(auth, signInRequestDto.getPassword());

		// 3. 토큰 생성
		// generateAccessToken 에서 authentication.getName() 이용
		String accessToken = jwtTokenProvider.generateAccessToken();
		String refreshToken = jwtTokenProvider.generateRefreshToken();

		// 4. Redis에 Refresh Token 저장
		// ToDo: 만료시간 설정
		RefreshToken refreshRedisToken = new RefreshToken(refreshToken, auth.getUuid());
		redisTemplate.opsForValue().set(refreshRedisToken.getRefreshToken(), auth.getUuid(), Duration.ofDays(1)); // Redis에 저장

		// Redis에서 값을 가져오기
		Object redisToken = redisTemplate.opsForValue().get(refreshRedisToken.getRefreshToken());

		return SignInResponseDto.toDto(accessToken, refreshToken, auth.getUuid());
	}

	@Override
	@Transactional
	public SignInResponseDto oAuthSign(OAuthSignUpRequestDto oAuthSignUpRequestDto) {
		// 소셜 회원가입 (1인 N계정 허용)
		// 소셜 로그인 클릭 -> 일치되는 정보 없음 -> 소셜 회원가입 클릭 하는식으로 연결
		// 정책: 소셜 로그인에 대한 약관 동의 시 api 호출 가능하도록 설정
		log.info("소셜 로그인 제공처: {}", oAuthSignUpRequestDto.getProvider());
		log.info("사용자 소셜 가입 시도: 로그인 ID = {}", oAuthSignUpRequestDto.getProviderAccountId());
		OAuth oAuth = oAuthRepository.findByProviderAndProviderAccountId(
			oAuthSignUpRequestDto.getProvider(), oAuthSignUpRequestDto.getProviderAccountId()).orElse(null);

		// 회원정보가 존재한다면 로그인 로직 수행
		if(oAuth != null) {
			// providerId 인증
			oAuthAuthenticate(oAuth.getProviderAccountId());

			String accessToken = jwtTokenProvider.generateAccessToken();
			String refreshToken = jwtTokenProvider.generateRefreshToken();

			// 4. Redis에 Refresh Token 저장
			// ToDo: 만료시간 설정
			RefreshToken refreshRedisToken = new RefreshToken(refreshToken, oAuth.getUuid());
			redisTemplate.opsForValue().set(refreshRedisToken.getRefreshToken(), oAuth.getUuid(), Duration.ofDays(1)); // Redis에 저장

			log.info("소셜 로그인 성공: token: {}, uuid: {}", refreshToken.substring(2,8), oAuth.getUuid());
			return SignInResponseDto.toDto(accessToken, refreshToken, oAuth.getUuid());

		}

		// 회원정보가 존재하지 않는다면 회원가입 로직 수행
		String createdUuid = UuidGenerator.generateSocialUuid();
		String socialNickname = generateRandomNickName();
		log.info(socialNickname);

		while (oAuthRepository.existsByUuid(createdUuid)){
			createdUuid = UuidGenerator.generateSocialUuid();
		}

		// User 서비스의 post api 실행
		// Todo: service 계층에서 컨트롤러 호출하는게 옳지 않아 보이는데, 마땅한 해결방안이 떠오르지 않음
		writeUserProfile(createdUuid, socialNickname);

		oAuthRepository.save(oAuthSignUpRequestDto.toEntity(createdUuid));

		log.info("소셜 회원가입 성공: {}", createdUuid);

		return SignInResponseDto.toDto("", "", createdUuid);
	}


	@Override
	@Transactional
	public RefreshTokenResponseDto refreshAccessToken(RefreshTokenRequestDto refreshTokenRequestDto) {

		// // 1. 요청 받은 리프레시 토큰값 추출
		String refreshTokenValue = refreshTokenRequestDto.getRefreshToken();
		log.info("요청받은 리프레시 토큰: {}", refreshTokenValue);

		// 2. Redis에서 요청받은 refreshToken에 대한 uuid 추출
		Object uuidObject = redisTemplate.opsForValue().get(refreshTokenValue);
		String uuid = null;
		log.info("Redis에서 조회된 uuidObject: {}", uuidObject);

		if (uuidObject != null) {
			uuid = uuidObject.toString(); // 이스케이프된 큰따옴표 제거 코드 삭제
		}
		log.info("Redis에서 조회된 uuid: {}", uuid);


		// 3-1. Redis에서 제대로 추출되지 않은 경우 예외처리
		if (uuid == null) {
			throw new BaseException(BaseResponseStatus.TOKEN_NOT_VALID);
		}


		// 3-2. 요청받은 uuid와 리프레쉬토큰에 대한 uuid가 다르다면 예외처리
		if (!uuid.equals(refreshTokenRequestDto.getUuid())) {
			throw new BaseException(BaseResponseStatus.TOKEN_NOT_VALID);
		}

		// 4. Redis에서 제대로 추출된 경우 로그 작성
		log.info("Redis에서 조회된 리프레시 토큰: {}", refreshTokenValue);

		String newAccessToken = jwtTokenProvider.generateAccessToken();

		// 현재 시간으로부터 1시간 후
		LocalDateTime expiredTime = LocalDateTime.now().plusHours(1);
		long accessTokenExpiredTime = expiredTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

		return RefreshTokenResponseDto.toDto(newAccessToken, accessTokenExpiredTime);
	}

	// 기존에는 토큰 생성 시 전달할 값이 필요하였으나 현재는 비밀번호 비교에 이용
	private Authentication authenticate(Auth auth, String inputPassword) {
		AuthUserDetails authUserDetail = new AuthUserDetails(auth);

		log.info("Input Password: {}", inputPassword);
		log.info("DB Password (Encoded): {}", authUserDetail.getPassword());
		log.info("Password Match: {}", passwordEncoder.matches(inputPassword, authUserDetail.getPassword()));

		// 비밀번호 비교
		if (!passwordEncoder.matches(inputPassword, authUserDetail.getPassword())) {
			throw new BaseException(BaseResponseStatus.FAILED_TO_LOGIN); // 비밀번호 불일치 시 예외 처리
		}

		// 비밀번호가 일치하는 경우 인증 객체 생성
		return new UsernamePasswordAuthenticationToken(
			authUserDetail.getUsername(),  // 로그인 ID
			inputPassword
		);
	}

	private Authentication oAuthAuthenticate(String providerAccountId) {
		log.info("providerAccountId : {}", providerAccountId);
		return authenticationManager.authenticate(
			new OAuthAuthenticationToken(providerAccountId)
		); // AuthenticationManager가 OAuthAuthenticationToken을 토큰을 생성 후 검증하고 인증된 토큰을 반환
	}

	// User 서비스에 전달할 데이터 생성
	public void writeUserProfile(String uuid, String nickname) {
		try {
			// 요청 바디 생성
			PostUserRequestDto requestDto = PostUserRequestDto.toDto(uuid, nickname);

			BaseResponse<Void> response = userServiceClient.writeUserProfile(requestDto);

			log.info("API URL: {}", response);

			//JSON parse error: Cannot deserialize value of type `int` from String "OK": not a valid `int` value


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

	// 소셜 로그인 시 랜덤 닉네임 생성
	public String generateRandomNickName(){
		String[] adjectives = {"happy", "funny", "famous", "smart", "strong", "shy", "sweet", "cool", "hot", "brave", "fast"};
		String[] nouns = {"puppy", "cat", "bird", "rabbit", "hamster", "guinea pig"};

		Random random = new Random();

		String adjective = adjectives[random.nextInt(adjectives.length)];
		String noun = nouns[random.nextInt(nouns.length)];
		int number = random.nextInt(10000000);

		return adjective + "_" + noun + number;

	}

}
