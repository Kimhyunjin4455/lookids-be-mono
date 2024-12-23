package lookids.comment.comment.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.comment.comment.domain.Comment;
import lookids.comment.comment.vo.in.CommentRequestVo;

@Getter
@NoArgsConstructor
public class CommentRequestDto {

	private String feedCode;
	private String feedUuid;
	private String userUuid;
	private String content;

	@Builder
	public CommentRequestDto(String feedCode, String feedUuid, String userUuid, String content) {
		this.feedCode = feedCode;
		this.feedUuid = feedUuid;
		this.userUuid = userUuid;
		this.content = content;
	}

	public static CommentRequestDto toDto(CommentRequestVo commentRequestVo, String userUuid) {
		return CommentRequestDto.builder()
			.feedCode(commentRequestVo.getFeedCode())
			.feedUuid(commentRequestVo.getFeedUuid())
			.userUuid(userUuid)
			.content(commentRequestVo.getContent())
			.build();
		//dto로 전환을 시켜줌.
	}

	public Comment toEntity(String commentCode) {
		return Comment.builder()
			.commentCode(commentCode)
			.feedCode(feedCode)
			.userUuid(userUuid)
			.content(content)
			.commentStatus(true)
			.build();
	}
}
