package lookids.mono.followblock.follow.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FollowUserProfileResponseVo {

	private String uuid;
	private String nickname;
	private String tag;
	private String image;

	@Builder
	public FollowUserProfileResponseVo(String uuid, String nickname, String tag, String image) {
		this.uuid = uuid;
		this.nickname = nickname;
		this.tag = tag;
		this.image = image;
	}

}
