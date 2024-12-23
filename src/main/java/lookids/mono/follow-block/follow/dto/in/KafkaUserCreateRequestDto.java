package Lookids.follow.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KafkaUserCreateRequestDto {

	private String uuid;

	@Builder
	public KafkaUserCreateRequestDto(String uuid) {
		this.uuid = uuid;
	}

}
