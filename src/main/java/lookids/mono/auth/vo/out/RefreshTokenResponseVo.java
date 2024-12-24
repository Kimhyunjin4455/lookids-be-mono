package lookids.mono.auth.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshTokenResponseVo {
	private String accessToken;
	private Long accessTokenExpiredTime;
}
