package lookids.mono.batch.follow.adaptor.in.kafka.event;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplyEvent {
	private String commentCode;
	private String feedCode;
	private String feedUuid;
	private String uuid;
	private String content;
	private LocalDateTime createdAt;
	private String parentCommentCode;
}
