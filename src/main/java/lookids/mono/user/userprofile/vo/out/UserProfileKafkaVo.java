package lookids.mono.user.userprofile.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class UserProfileKafkaVo {
	private String uuid;
	private String nickname;
	private String tag;
	private String image;
}
