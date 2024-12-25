package lookids.mono.followblock.follow.dto.in;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lookids.mono.followblock.follow.domain.Follow;
import lookids.mono.followblock.follow.vo.in.FollowRequestVo;

@Getter
@Setter
@NoArgsConstructor
public class FollowRequestDto {

	private Long id;
	private String followerUuid;
	private String followingUuid;
	private LocalDateTime createdAt;

	@Builder
	public FollowRequestDto(Long id, String followerUuid, String followingUuid, LocalDateTime createdAt) {
		this.id = id;
		this.followerUuid = followerUuid;
		this.followingUuid = followingUuid;
		this.createdAt = createdAt;
	}

	public static FollowRequestDto toDto(String uuid, FollowRequestVo followRequestVo) {
		return FollowRequestDto.builder().followerUuid(followRequestVo.getFollowerUuid()).followingUuid(uuid).build();
	}

	public Follow toEntity() {
		return Follow.builder()
			.followerUuid(followerUuid)
			.followingUuid(followingUuid)
			.createdAt(LocalDateTime.now())
			.build();
	}

}
