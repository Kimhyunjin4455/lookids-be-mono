package lookids.mono.batch.feed.adaptor.in.kafka.event;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FeedEvent {
	private String feedCode;
	private String uuid;
	private List<String> petCode;
	private String content;
	private List<String> tags;
	private boolean state;
	private List<String> mediaUrl;
	private LocalDateTime createdAt;
}
