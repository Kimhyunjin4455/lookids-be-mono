package lookids.mono.batch.comment.application.port.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentLogSaveDto {
	private String commentCode;
	private String feedCode;
	private String uuid;
	private LocalDateTime createdAt;
	private String logType;
	private String parentCommentCode;
	private String commentType;

	@Builder
	public CommentLogSaveDto(String commentCode, String feedCode, String uuid, LocalDateTime createdAt, String logType,
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
