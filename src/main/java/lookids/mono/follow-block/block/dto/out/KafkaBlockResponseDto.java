package Lookids.block.dto.out;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class KafkaBlockResponseDto {

	private String uuid;
	private List<String> blockUuid;

	@Builder
	public KafkaBlockResponseDto(String uuid, List<String> blockUuid) {
		this.uuid = uuid;
		this.blockUuid = blockUuid;
	}

}
