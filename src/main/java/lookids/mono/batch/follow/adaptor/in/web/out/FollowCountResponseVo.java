package lookids.mono.batch.follow.adaptor.in.web.out;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FollowCountResponseVo {
	private Integer followingCount;
	private Integer followerCount;
}
