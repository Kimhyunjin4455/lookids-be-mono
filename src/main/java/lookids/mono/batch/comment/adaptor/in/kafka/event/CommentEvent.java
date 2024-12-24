package lookids.mono.batch.comment.adaptor.in.kafka.event;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class CommentEvent {
	private String commentCode;
	private String feedCode;
	private String feedUuid;
	private String uuid;
	private String content;
	private LocalDateTime createdAt;
}