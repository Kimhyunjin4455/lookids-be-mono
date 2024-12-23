package Lookids.follow.dto.in;

import java.time.LocalDateTime;

import Lookids.follow.domain.Follow;
import Lookids.follow.vo.in.FollowRequestVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
		return FollowRequestDto.builder()
			.followerUuid(followRequestVo.getFollowerUuid())
			.followingUuid(uuid)
			.build();
	}

	public Follow toEntity() {
		return Follow.builder()
			.followerUuid(followerUuid)
			.followingUuid(followingUuid)
			.createdAt(LocalDateTime.now())
			.build();
	}

}
