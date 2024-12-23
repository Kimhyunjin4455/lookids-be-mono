package lookids.batch.follow.adaptor.in.kafka.event;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FollowEvent {
	private String receiverUuid;
	private String senderUuid;
	private LocalDateTime createdAt;
}
