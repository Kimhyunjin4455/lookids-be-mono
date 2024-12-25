package lookids.mono.commentread.adaptor.in.web.vo.out;

import java.time.Instant;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentReadResponseVo {
	private String commentCode;
	private String uuid;
	private String nickname;
	private String tag;
	private String image;
	private String content;
	private Instant createdAt;
	private Integer replyCount;
}
