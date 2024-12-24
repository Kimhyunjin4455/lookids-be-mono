package lookids.mono.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lookids.mono.auth.repository.AuthRepository;
import lookids.mono.auth.userdetails.AuthUserDetails;

@Service
@RequiredArgsConstructor
public class AuthUserDetailService implements UserDetailsService {

	private final AuthRepository authRepository;

	@Override
	public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
		return new AuthUserDetails(
			authRepository.findByUuid(uuid).orElseThrow(() -> new UsernameNotFoundException(uuid)));
	}

}
