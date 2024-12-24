package lookids.mono.batch.comment.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class CommentLog {

	private String commentCode;
	private String feedCode;
	private String uuid;
	private LocalDateTime createdAt;
	private String logType;
	private String parentCommentCode;
	private String commentType;

	@Builder
	public CommentLog(String commentCode, String feedCode, String uuid, LocalDateTime createdAt, String logType,
		String parentCommentCode, String commentType) {
		this.commentCode = commentCode;
		this.feedCode = feedCode;
		this.uuid = uuid;
		this.createdAt = createdAt;
		this.logType = logType;
		this.parentCommentCode = parentCommentCode;
		this.commentType = commentType;
	}
}
