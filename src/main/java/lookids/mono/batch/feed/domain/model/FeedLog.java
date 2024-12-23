package lookids.batch.feed.domain.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class FeedLog {
	private Long id;
	private String feedCode;
	private String uuid;
	private List<String> petCode;
	private String logType;
	private LocalDateTime createdAt;
	private Boolean processed;

	@Builder
	public FeedLog(Long id, String uuid, String feedCode, List<String> petCode, String logType, LocalDateTime createdAt,
		Boolean processed) {
		this.id = id;
		this.feedCode = feedCode;
		this.uuid = uuid;
		this.logType = logType;
		this.petCode = petCode;
		this.createdAt = createdAt;
		this.processed = processed;
	}
}
