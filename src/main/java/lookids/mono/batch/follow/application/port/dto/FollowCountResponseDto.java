package lookids.mono.batch.follow.application.port.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowCountResponseDto {
	private Integer followingCount;
	private Integer followerCount;

	@Builder
	public FollowCountResponseDto(Integer followingCount, Integer followerCount) {
		this.followingCount = followingCount;
		this.followerCount = followerCount;
	}
}
