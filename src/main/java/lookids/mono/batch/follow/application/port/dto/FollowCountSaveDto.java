package lookids.mono.batch.follow.application.port.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowCountSaveDto {
	private Long id;
	private String uuid;
	private Integer followingCount;
	private Integer followerCount;
	private LocalDateTime updateAt;

	@Builder
	public FollowCountSaveDto(Long id, String uuid, Integer followingCount, Integer followerCount,
		LocalDateTime updateAt) {
		this.id = id;
		this.uuid = uuid;
		this.followingCount = followingCount;
		this.followerCount = followerCount;
		this.updateAt = updateAt;
	}
}
