package Lookids.follow.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KafkaFollowRequestDto {

	private String uuid;

	@Builder
	public KafkaFollowRequestDto(String uuid) {
		this.uuid = uuid;
	}

}
