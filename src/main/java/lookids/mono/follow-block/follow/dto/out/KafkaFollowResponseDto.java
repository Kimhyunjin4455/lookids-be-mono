package Lookids.follow.dto.out;

import java.util.List;

import Lookids.follow.domain.Follow;
import lombok.Builder;
import lombok.Getter;

@Getter
public class KafkaFollowResponseDto {

	private String uuid;
	private List<String> followUuid;

	@Builder
	public KafkaFollowResponseDto(String uuid, List<String> followUuid) {
		this.uuid = uuid;
		this.followUuid = followUuid;
	}

	public static KafkaFollowResponseDto toDto(String uuid, List<Follow> follow) {
		return KafkaFollowResponseDto.builder()
			.uuid(uuid)
			.followUuid(follow.stream().map(Follow::getFollowerUuid).toList())
			.build();
	}

}
