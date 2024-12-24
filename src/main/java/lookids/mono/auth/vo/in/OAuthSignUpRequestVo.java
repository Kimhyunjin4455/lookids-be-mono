package lookids.mono.auth.vo.in;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class OAuthSignUpRequestVo {
	private String provider;
	private String providerAccountId;
}
