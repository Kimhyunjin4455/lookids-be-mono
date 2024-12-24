package lookids.mono.auth.vo.in;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class RefreshTokenRequestVo {
	private String refreshToken;
}
