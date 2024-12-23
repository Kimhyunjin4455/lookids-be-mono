package lookids.comment.comment.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.comment.comment.domain.Comment;
import lookids.comment.comment.vo.in.ReplyRequestVo;

@Getter
@NoArgsConstructor
public class ReplyRequestDto {
	private String feedCode;
	private String feedUuid;
	private String userUuid;
	private String content;
	private String parentCommentCode;

	@Builder
	public ReplyRequestDto(String feedCode, String feedUuid, String userUuid, String content,
		String parentCommentCode) {
		this.feedCode = feedCode;
		this.feedUuid = feedUuid;
		this.userUuid = userUuid;
		this.content = content;
		this.parentCommentCode = parentCommentCode;
	}

	public static ReplyRequestDto toDto(ReplyRequestVo replyRequestVo, String userUuid) {
		return ReplyRequestDto.builder()
			.feedCode(replyRequestVo.getFeedCode())
			.feedUuid(replyRequestVo.getFeedUuid())
			.userUuid(userUuid)
			.content(replyRequestVo.getContent())
			.parentCommentCode(replyRequestVo.getParentCommentCode())
			.build();
		//dto로 전환을 시켜줌.
	}

	public Comment toEntity(String commentCode) {
		return Comment.builder()
			.commentCode(commentCode)
			.feedCode(feedCode)
			.userUuid(userUuid)
			.content(content)
			.parentCommentCode(parentCommentCode)
			.commentStatus(true)
			.build();
	}
}
