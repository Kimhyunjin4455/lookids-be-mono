package lookids.batch.feed.adaptor.in.kafka.event;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FeedDeleteEvent {

	private String feedCode;
	private String uuid;
	private LocalDateTime createdAt;
}