package lookids.batch.follow.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class FollowCount {
	private Long id;
	private String uuid;
	private Integer followingCount;
	private Integer followerCount;
	private LocalDateTime updateAt;

	@Builder
	public FollowCount(Long id, String uuid, Integer followingCount, Integer followerCount, LocalDateTime updateAt) {
		this.id = id;
		this.uuid = uuid;
		this.followingCount = followingCount;
		this.followerCount = followerCount;
		this.updateAt = updateAt;
	}
}
