package lookids.mono.user.userprofile.vo.in;

import java.time.LocalDate;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserProfileNicknameVo {

	private String nickname;
	private LocalDate birthDate;
	private String gender;
}
