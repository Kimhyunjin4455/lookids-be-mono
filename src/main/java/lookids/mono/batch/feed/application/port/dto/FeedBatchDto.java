package lookids.mono.batch.feed.application.port.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedBatchDto {
	private String uuid;
	private Integer count;

	@Builder
	public FeedBatchDto(String uuid, Integer count) {

		this.uuid = uuid;
		this.count = count;
	}
}
