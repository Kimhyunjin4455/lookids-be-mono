package lookids.mono.auth.userdetails;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.auth.domain.Auth;

@ToString
@Slf4j
@Getter
@NoArgsConstructor
public class AuthUserDetails implements UserDetails {

	// 엔티티가 아닌 DTO 역할
	// User 엔티티의 정보를 Spring Security에서 사용할 수 있는 형태로 변환
	// SecurityContext에 저장되어 인증된 사용자 정보로 활용

	private String loginId;
	private String password;
	private String email;

	public AuthUserDetails(Auth auth) {
		this.loginId = auth.getLoginId();
		this.password = auth.getPassword();
		this.email = auth.getUserEmail();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.loginId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
