package lookids.mono.batch.feed.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class FeedCount {
	private Long id;
	private String uuid;
	private Integer count;
	private LocalDateTime updateAt;

	@Builder
	public FeedCount(Long id, String uuid, Integer count, LocalDateTime updateAt) {
		this.id = id;
		this.uuid = uuid;
		this.count = count;
		this.updateAt = updateAt;
	}
}
