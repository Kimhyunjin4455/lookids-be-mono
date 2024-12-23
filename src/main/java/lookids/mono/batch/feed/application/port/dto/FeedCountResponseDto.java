package lookids.batch.feed.application.port.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedCountResponseDto {
	private Integer count;

	@Builder
	public FeedCountResponseDto(Integer count) {
		this.count = count;
	}
}
