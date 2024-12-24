package lookids.mono.auth.vo.in;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class SignInRequestVo {

	private String loginId;
	private String password;

}