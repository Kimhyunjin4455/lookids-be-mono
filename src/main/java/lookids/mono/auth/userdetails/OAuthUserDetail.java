package lookids.auth.auth.userdetails;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import lookids.auth.auth.domain.OAuth;

@ToString
@Slf4j
@Getter
@NoArgsConstructor
public class OAuthUserDetail implements UserDetails {
	private String provider;
	private String providerAccountId;
	// private String socialId;

	public OAuthUserDetail(OAuth oAuth) {
		this.provider = oAuth.getProvider();
		this.providerAccountId = oAuth.getProviderAccountId();
		// this.socialId = oAuth.getSocialId();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() { return this.providerAccountId; }

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
