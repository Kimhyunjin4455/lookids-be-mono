package lookids.mono.common.entity;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class OAuthAuthenticationToken extends AbstractAuthenticationToken {
	// OAuth용 커스텀 인증 토큰
	private final Object principal; // // 사용자 식별자 저장

	// 초기에는 인증되지 않은 상태(setAuthenticated(false))로 설정
	// OAuth 인증 프로세스의 시작점
	// 인증이 필요한 사용자 정보를 담는 컨테이너
	// Spring Security의 인증 시스템에 전달될 초기 상태를 설정
	// 이 토큰은 나중에 AuthenticationManager에 의해 처리되어 인증
	public OAuthAuthenticationToken(String providerAccountId) {
		super(null);
		this.principal = providerAccountId;
		setAuthenticated(false);
	}

	@Override
	public Object getCredentials() {
		return null; // OAuth에서는 자격 증명이 필요하지 않음
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}
}