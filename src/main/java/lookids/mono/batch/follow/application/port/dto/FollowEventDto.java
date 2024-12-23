package lookids.batch.follow.application.port.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowEventDto {
	private String senderUuid;
	private String receiverUuid;
	private LocalDateTime createdAt;

	@Builder
	public FollowEventDto(String senderUuid, String receiverUuid, List<String> petCode, String logType,
		LocalDateTime createdAt) {
		this.senderUuid = senderUuid;
		this.receiverUuid = receiverUuid;
		this.createdAt = createdAt;
	}
}
