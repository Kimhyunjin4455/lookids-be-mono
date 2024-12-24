package lookids.mono.batch.feed.application.port.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedCountSaveDto {
	private Long id;
	private String uuid;
	private Integer count;
	private LocalDateTime updateAt;

	@Builder
	public FeedCountSaveDto(Long id, String uuid, Integer count, LocalDateTime updateAt) {
		this.id = id;
		this.uuid = uuid;
		this.count = count;
		this.updateAt = updateAt;
	}
}
