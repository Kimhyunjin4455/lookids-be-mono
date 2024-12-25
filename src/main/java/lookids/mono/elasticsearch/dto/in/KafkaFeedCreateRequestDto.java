package lookids.mono.elasticsearch.dto.in;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KafkaFeedCreateRequestDto {

	private String feedCode;
	private List<String> petCode;
	private List<String> tagList;
	private List<String> mediaUrlList;

	@Builder
	public KafkaFeedCreateRequestDto(String feedCode, List<String> petCode, List<String> tagList,
		List<String> mediaUrlList) {
		this.feedCode = feedCode;
		this.petCode = petCode;
		this.tagList = tagList;
		this.mediaUrlList = mediaUrlList;
	}

}
