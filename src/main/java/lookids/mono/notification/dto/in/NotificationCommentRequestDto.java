package lookids.mono.notification.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotificationCommentRequestDto {
	private String uuid;
	private String receiverUuid;
	private String content;
	private String feedCode;

	@Builder
	public NotificationCommentRequestDto(String uuid, String receiverUuid, String content, String feedCode) {
		this.uuid = uuid;
		this.receiverUuid = receiverUuid;
		this.content = content;
		this.feedCode = feedCode;
	}
}
