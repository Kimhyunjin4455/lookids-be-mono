package lookids.mono.batch.feed.application.port.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedCreateEventDto {
	private String feedCode;
	private String uuid;
	private List<String> petCode;
	private LocalDateTime createdAt;

	@Builder
	public FeedCreateEventDto(String feedCode, List<String> petCode, String uuid, LocalDateTime createdAt) {
		this.feedCode = feedCode;
		this.petCode = petCode;
		this.uuid = uuid;
		this.createdAt = createdAt;
	}
}