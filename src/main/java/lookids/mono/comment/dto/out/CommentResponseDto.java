package lookids.comment.comment.dto.out;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.comment.comment.domain.Comment;
import lookids.comment.comment.vo.out.CommentKafkaVo;
import lookids.comment.comment.vo.out.CommentResponseVo;
import lookids.comment.comment.vo.out.ReplyKafkaVo;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

	private String commentCode;
	private String feedCode;
	private String userUuid;
	private String content;
	private LocalDateTime createdAt;
	private String parentCommentCode;

	@Builder
	public CommentResponseDto(String commentCode, String feedCode, String userUuid, String content,
		LocalDateTime createdAt, String parentCommentCode) {
		this.commentCode = commentCode;
		this.feedCode = feedCode;
		this.userUuid = userUuid;
		this.content = content;
		this.createdAt = createdAt;
		this.parentCommentCode = parentCommentCode;
	}

	public static CommentResponseDto toDto(Comment comment) {
		return CommentResponseDto.builder()
			.commentCode(comment.getCommentCode())
			.feedCode(comment.getFeedCode())
			.userUuid(comment.getUserUuid())
			.content(comment.getContent())
			.createdAt(comment.getCreatedAt())
			.parentCommentCode(comment.getParentCommentCode())
			.build();
	}

	public CommentResponseVo toVo() {
		return CommentResponseVo.builder()
			.commentCode(commentCode)
			.feedCode(feedCode)
			.uuid(userUuid)
			.content(content)
			.createdAt(createdAt)
			.parentCommentCode(parentCommentCode)
			.build();
	}

	public CommentKafkaVo toCommentKafkaVo(String feedUuid) {
		return CommentKafkaVo.builder()
			.commentCode(commentCode)
			.feedCode(feedCode)
			.feedUuid(feedUuid)
			.uuid(userUuid)
			.content(content)
			.createdAt(createdAt)
			.build();
	}

	public ReplyKafkaVo toReplyKafkaVo(String feedUuid) {
		return ReplyKafkaVo.builder()
			.commentCode(commentCode)
			.feedCode(feedCode)
			.feedUuid(feedUuid)
			.parentCommentCode(parentCommentCode)
			.uuid(userUuid)
			.content(content)
			.createdAt(createdAt)
			.build();
	}

	public CommentKafkaVo toCommentDeleteKafkaVo() {
		return CommentKafkaVo.builder()
			.commentCode(commentCode)
			.feedCode(feedCode)
			.uuid(userUuid)
			.content(content)
			.createdAt(LocalDateTime.now())
			.build();
	}

	public ReplyKafkaVo toReplyDeleteKafkaVo() {
		return ReplyKafkaVo.builder()
			.commentCode(commentCode)
			.feedCode(feedCode)
			.parentCommentCode(parentCommentCode)
			.uuid(userUuid)
			.content(content)
			.createdAt(LocalDateTime.now())
			.build();
	}

}
