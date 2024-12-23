package lookids.batch.follow.application.port.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowLogSaveDto {
	private Long id;
	private String senderUuid;
	private String receiverUuid;
	private String logType;
	private LocalDateTime createdAt;
	private Boolean processed;

	@Builder
	public FollowLogSaveDto(Long id, String senderUuid, String receiverUuid, String logType, LocalDateTime createdAt,
		Boolean processed) {
		this.id = id;
		this.senderUuid = senderUuid;
		this.receiverUuid = receiverUuid;
		this.logType = logType;
		this.createdAt = createdAt;
		this.processed = processed;
	}
}
