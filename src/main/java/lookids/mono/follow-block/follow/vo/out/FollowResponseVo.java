package Lookids.follow.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FollowResponseVo {

	private String followerUuid;
	private String followingUuid;

	@Builder
	public FollowResponseVo(String followerUuid, String followingUuid) {
		this.followerUuid = followerUuid;
		this.followingUuid = followingUuid;
	}

}
