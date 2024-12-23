package lookids.auth.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lookids.auth.auth.userdetails.AuthUserDetails;
import lookids.auth.auth.repository.AuthRepository;

@Service
@RequiredArgsConstructor
public class AuthUserDetailService implements UserDetailsService {

	private final AuthRepository authRepository;
	@Override
	public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
		return new AuthUserDetails(authRepository.findByUuid(uuid).orElseThrow(() -> new UsernameNotFoundException(uuid)));
	}

}
