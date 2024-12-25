package lookids.mono.followblock.follow.dto.out;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class KafkaAlarmFollowResponseDto {

	private String receiverUuid;
	private String senderUuid;
	private LocalDateTime createdAt;

	@Builder
	public KafkaAlarmFollowResponseDto(String receiverUuid, String senderUuid, LocalDateTime createdAt) {
		this.receiverUuid = receiverUuid;
		this.senderUuid = senderUuid;
		this.createdAt = LocalDateTime.now();
	}

}
