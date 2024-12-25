package lookids.mono.feed.vo.in;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FeedRequestVo {

	private List<String> petCode;
	private String content;
	private List<String> tagList;
	private List<String> mediaUrlList;

}
