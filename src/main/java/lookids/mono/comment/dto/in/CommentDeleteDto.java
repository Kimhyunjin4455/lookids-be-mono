package lookids.comment.comment.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.comment.comment.domain.Comment;
import lookids.comment.comment.vo.in.CommentDeleteVo;

@Getter
@NoArgsConstructor
public class CommentDeleteDto {

	private String commentCode;
	private String userUuid;

	@Builder
	public CommentDeleteDto(String commentCode, String userUuid) {
		this.commentCode = commentCode;
		this.userUuid = userUuid;
	}

	public static CommentDeleteDto toDto(CommentDeleteVo commentDeleteVo, String userUuid) {
		return CommentDeleteDto.builder()
			.commentCode(commentDeleteVo.getCommentCode())
			.userUuid(userUuid)
			.build();
	}

	public Comment toEntity(Comment comment) {
		return Comment.builder()
			.id(comment.getId())
			.feedCode(comment.getFeedCode())
			.commentCode(comment.getCommentCode())
			.parentCommentCode(comment.getParentCommentCode())
			.content(comment.getContent())
			.userUuid(comment.getUserUuid())
			.commentStatus(false)
			.build();
	}
}
