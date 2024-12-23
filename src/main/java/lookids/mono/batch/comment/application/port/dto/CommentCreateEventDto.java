package lookids.batch.comment.application.port.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreateEventDto {
	private String commentCode;
	private String feedCode;
	private String uuid;
	private LocalDateTime createdAt;

	@Builder
	public CommentCreateEventDto(String commentCode, String feedCode, String uuid, LocalDateTime createdAt) {
		this.commentCode = commentCode;
		this.feedCode = feedCode;
		this.uuid = uuid;
		this.createdAt = createdAt;
	}
}
