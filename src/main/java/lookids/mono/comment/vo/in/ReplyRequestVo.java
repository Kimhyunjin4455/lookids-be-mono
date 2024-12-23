package lookids.comment.comment.vo.in;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ReplyRequestVo {
	private String feedCode;
	private String feedUuid;
	private String parentCommentCode;
	private String content;
}
