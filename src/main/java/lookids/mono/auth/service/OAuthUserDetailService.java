package lookids.auth.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lookids.auth.auth.userdetails.OAuthUserDetail;
import lookids.auth.auth.repository.OAuthRepository;
import lookids.auth.common.entity.BaseResponseStatus;
import lookids.auth.common.exception.BaseException;

@Service
@RequiredArgsConstructor
public class OAuthUserDetailService implements UserDetailsService {

	private final OAuthRepository oAuthRepository;

	@Override
	public UserDetails loadUserByUsername(String providerAccountId) throws UsernameNotFoundException {
		return new OAuthUserDetail(oAuthRepository.findByProviderAccountId(providerAccountId).orElseThrow(
			() -> new BaseException(BaseResponseStatus.NO_EXIST_USER)
		));
	}
}

