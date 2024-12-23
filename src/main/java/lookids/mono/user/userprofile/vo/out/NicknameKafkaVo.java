package lookids.mono.user.userprofile.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class NicknameKafkaVo {
	private String uuid;
	private String nickname;
	private String tag;
}
