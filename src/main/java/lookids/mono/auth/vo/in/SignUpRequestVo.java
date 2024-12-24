package lookids.mono.auth.vo.in;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class SignUpRequestVo {
	private String loginId;
	private String password;
	private String email;
	private String nickname;
}
