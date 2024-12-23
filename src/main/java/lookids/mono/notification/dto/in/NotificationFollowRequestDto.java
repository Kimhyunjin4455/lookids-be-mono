package lookids.alarm.notification.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotificationFollowRequestDto {
	private String senderUuid;
	private String receiverUuid;
	private String type;

	@Builder
	public NotificationFollowRequestDto(
		String senderUuid,
		String receiverUuid,
		String type
	) {
		this.senderUuid = senderUuid;
		this.receiverUuid = receiverUuid;
		this.type = type;
	}
}
