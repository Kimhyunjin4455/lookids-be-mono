package lookids.mono.elasticsearch.vo;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SearchFeedResponseVo {

	private String feedCode;
	private List<String> tagList;
	private List<String> mediaUrlList;

	@Builder
	public SearchFeedResponseVo(String feedCode, List<String> tagList, List<String> mediaUrlList) {
		this.feedCode = feedCode;
		this.tagList = tagList;
		this.mediaUrlList = mediaUrlList;
	}

}
