package lookids.mono.commentread.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedCount {

	private String feedCode;
	private Integer totalCommentCount;

	@Builder
	public FeedCount(String feedCode, Integer totalCommentCount) {
		this.feedCode = feedCode;
		this.totalCommentCount = totalCommentCount;
	}
}
