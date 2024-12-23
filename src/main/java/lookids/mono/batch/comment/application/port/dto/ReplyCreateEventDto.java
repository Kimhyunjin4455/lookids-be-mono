package lookids.batch.comment.application.port.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplyCreateEventDto {
	private String commentCode;
	private String feedCode;
	private String uuid;
	private LocalDateTime createdAt;
	private String parentCommentCode;

	@Builder
	public ReplyCreateEventDto(String commentCode, String feedCode, String uuid, String content,
		LocalDateTime createdAt, String parentCommentCode) {
		this.commentCode = commentCode;
		this.feedCode = feedCode;
		this.uuid = uuid;
		this.createdAt = createdAt;
		this.parentCommentCode = parentCommentCode;
	}
}
