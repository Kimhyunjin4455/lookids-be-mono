package lookids.mono.followblock.follow.dto.out;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lookids.mono.followblock.follow.domain.Follow;

@Getter
public class KafkaFollowerResponseDto {

	private String uuid;
	private List<String> followerUuid;

	@Builder
	public KafkaFollowerResponseDto(String uuid, List<String> followerUuid) {
		this.uuid = uuid;
		this.followerUuid = followerUuid;
	}

	public static KafkaFollowerResponseDto toDto(String uuid, List<Follow> follower) {
		return KafkaFollowerResponseDto.builder()
			.uuid(uuid)
			.followerUuid(follower.stream().map(Follow::getFollowingUuid).toList())
			.build();
	}

}
