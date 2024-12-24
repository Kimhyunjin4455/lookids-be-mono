package lookids.mono.batch.follow.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class FollowLog {
	private Long id;
	private String senderUuid;
	private String receiverUuid;
	private String logType;
	private LocalDateTime createdAt;
	private Boolean processed;

	@Builder
	public FollowLog(Long id, String senderUuid, String receiverUuid, String logType, LocalDateTime createdAt,
		Boolean processed) {
		this.id = id;
		this.senderUuid = senderUuid;
		this.receiverUuid = receiverUuid;
		this.logType = logType;
		this.createdAt = createdAt;
		this.processed = processed;
	}
}
