package lookids.elasticsearch.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KafkaFeedDeleteRequestDto {

	private String feedCode;

	@Builder
	public KafkaFeedDeleteRequestDto(String feedCode) {
		this.feedCode = feedCode;
	}

}
