package lookids.batch.follow.application.port.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowBatchDto {
	private String uuid;
	private Integer followingCount;
	private Integer followerCount;

	@Builder
	public FollowBatchDto(String uuid, Integer followingCount, Integer followerCount) {
		this.uuid = uuid;
		this.followingCount = followingCount;
		this.followerCount = followerCount;
	}
}
