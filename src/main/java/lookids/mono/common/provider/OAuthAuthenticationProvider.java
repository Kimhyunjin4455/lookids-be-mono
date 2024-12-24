package lookids.mono.common.provider;

import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.auth.service.OAuthUserDetailService;
import lookids.mono.auth.userdetails.OAuthUserDetail;
import lookids.mono.common.entity.OAuthAuthenticationToken;

@Slf4j
@RequiredArgsConstructor
@Component
@Primary
public class OAuthAuthenticationProvider implements AuthenticationProvider {

	private final OAuthUserDetailService authUserDetailService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (!(authentication instanceof OAuthAuthenticationToken)) {
			return null; // OAuthAuthenticationToken만 지원
		}

		String providerAccountId = authentication.getName(); // / providerAccountId 획득
		log.info("OAuthName: {}", providerAccountId);

		OAuthUserDetail oAuthUserDetail = (OAuthUserDetail)authUserDetailService.loadUserByUsername(
			providerAccountId); // providerAccountId
		return new UsernamePasswordAuthenticationToken(oAuthUserDetail, null, oAuthUserDetail.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return OAuthAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
