package lookids.mono.common.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class JwtTokenProvider {

	private final Environment env;

	/**
	 * 액세스 토큰 생성
	 *
	 * @return 클레임 정보와 사용자 정보를 기반으로 jwt 토큰 생성
	 */
	public String generateAccessToken() { // 게이트웨이에서 토큰을 뜯어 loginId, providerId를 사용할 일이 없다면 여기서 다른 식별할 값 입력 가능(String name)
		Date now = new Date();
		Date expiration = new Date(
			now.getTime() + env.getProperty("jwt.token.access-expire-time", Long.class).longValue());

		return Jwts.builder()
			// .claim("username", authentication.getName()) // 사용자 이름 -> 토큰 탈취 시 사용자 이름을 알 수 있음 (일반회원 loginId, 소셜회원)
			.claim("issuedAt", now) // 생성 시간
			.claim("expiration", expiration) // 만료 시간
			.signWith(getSignKey()) // 서명
			.compact();
	}

	/**
	 * 리프레시 토큰 생성
	 *
	 * @return 클레임 정보와 사용자 정보를 기반으로 jwt 토큰 생성
	 */
	public String generateRefreshToken() {
		Date now = new Date();
		Date expiration = new Date(
			now.getTime() + env.getProperty("jwt.token.refresh-expire-time", Long.class).longValue());

		return Jwts.builder()
			// .claim("username", authentication.getName()) // 사용자 이름
			.claim("issuedAt", now) // 발행 시간
			.claim("expiration", expiration) // 만료 시간
			.signWith(getSignKey()) // 서명
			.compact();
	}

	// 서명 키 가져오기
	public Key getSignKey() {
		String secretKey = env.getProperty("jwt.secret-key");

		if (secretKey == null) {
			throw new IllegalArgumentException("JWT secret key must not be null");
		}

		return Keys.hmacShaKeyFor(secretKey.getBytes());
	}
}