package lookids.mono.auth.vo.in;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class OAuthSignInRequestVo {
	private String provider;
	private String providerAccountId;
}
