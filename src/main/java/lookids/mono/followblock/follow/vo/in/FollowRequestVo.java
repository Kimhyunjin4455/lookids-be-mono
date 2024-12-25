package lookids.mono.followblock.follow.vo.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class FollowRequestVo {

	private String followerUuid;

	@Builder
	public FollowRequestVo(String followerUuid) {
		this.followerUuid = followerUuid;
	}

}
