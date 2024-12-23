package lookids.alarm.notification.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotificationFavoriteRequestDto {
	private String senderUuid;
	private String receiverUuid;
	private String feedCode;
	private String type;

	@Builder
	public NotificationFavoriteRequestDto(
		String senderUuid,
		String receiverUuid,
		String feedCode,
		String type
	) {
		this.senderUuid = senderUuid;
		this.receiverUuid = receiverUuid;
		this.feedCode = feedCode;
		this.type = type;
	}
}
