package Lookids.follow.dto.out;

import java.util.List;

import Lookids.follow.domain.Follow;
import lombok.Builder;
import lombok.Getter;

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
