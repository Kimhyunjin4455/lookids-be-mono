package lookids.mono.auth.vo.in;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class EmailVerifyRequestVo {
	private String email;
	private String key;
}
