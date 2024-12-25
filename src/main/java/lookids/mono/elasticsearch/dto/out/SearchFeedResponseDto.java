package lookids.mono.elasticsearch.dto.out;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lookids.mono.elasticsearch.domain.SearchFeed;
import lookids.mono.elasticsearch.vo.SearchFeedResponseVo;

@Getter
public class SearchFeedResponseDto {

	private String feedCode;
	private List<String> tagList;
	private List<String> mediaUrlList;

	@Builder
	public SearchFeedResponseDto(String feedCode, List<String> tagList, List<String> mediaUrlList) {
		this.feedCode = feedCode;
		this.tagList = tagList;
		this.mediaUrlList = mediaUrlList;
	}

	public static SearchFeedResponseDto toDto(SearchFeed searchFeed) {
		return SearchFeedResponseDto.builder()
			.feedCode(searchFeed.getFeedCode())
			.tagList(searchFeed.getTagList())
			.mediaUrlList(searchFeed.getMediaUrlList())
			.build();
	}

	public SearchFeedResponseVo toVo() {
		return SearchFeedResponseVo.builder()
			.feedCode(this.feedCode)
			.tagList(this.tagList)
			.mediaUrlList(this.mediaUrlList)
			.build();
	}
}
