package lookids.mono.followblock.follow.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lookids.mono.followblock.follow.vo.out.FollowResponseVo;

@Getter
@Setter
@NoArgsConstructor
public class FollowResponseDto {

	private String followerUuid;
	private String followingUuid;

	@Builder
	public FollowResponseDto(String followerUuid, String followingUuid) {
		this.followerUuid = followerUuid;
		this.followingUuid = followingUuid;
	}

	public FollowResponseVo toVo() {
		return FollowResponseVo.builder().followerUuid(this.followerUuid).followingUuid(this.followingUuid).build();
	}
}
