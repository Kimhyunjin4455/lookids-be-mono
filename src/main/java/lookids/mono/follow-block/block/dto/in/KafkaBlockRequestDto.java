package Lookids.block.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KafkaBlockRequestDto {

	private String uuid;

	@Builder
	public KafkaBlockRequestDto(String uuid) {
		this.uuid = uuid;
	}

}
