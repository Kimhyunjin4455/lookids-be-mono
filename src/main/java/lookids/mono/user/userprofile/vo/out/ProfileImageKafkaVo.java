package lookids.mono.user.userprofile.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class ProfileImageKafkaVo {
	private String uuid;
	private String image;
}
