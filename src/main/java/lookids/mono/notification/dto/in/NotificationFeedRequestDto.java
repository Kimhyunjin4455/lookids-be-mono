package lookids.alarm.notification.dto.in;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotificationFeedRequestDto {
	private String senderUuid;
	private List<String> receiverUuidList;
	private String content;
	private String mediaUrl;
	private String feedCode;
	private String type;

	@Builder
	public NotificationFeedRequestDto(
		String senderUuid,
		List<String> receiverUuidList,
		String content,
		String mediaUrl,
		String feedCode,
		String type
	) {
		this.senderUuid = senderUuid;
		this.receiverUuidList = receiverUuidList;
		this.content = content;
		this.mediaUrl = mediaUrl;
		this.feedCode = feedCode;
		this.type = type;
	}

	// SubscribeKafkaRequestDto는 Listener를 통해 받은 dto
	// public static NotificationFeedRequestDto toDto(NotificationKafkaRequestDto kafkaSubscribeRequestDto) {
	// 	return NotificationFeedRequestDto.builder()
	// 		.senderUuid(kafkaSubscribeRequestDto.getSenderUuid())
	// 		.receiverUuidList(kafkaSubscribeRequestDto.getReceiverUuidList())
	// 		.content(kafkaSubscribeRequestDto.getContent())
	// 		.mediaUrl(kafkaSubscribeRequestDto.getMediaUrl())
	// 		.type(kafkaSubscribeRequestDto.getType())
	// 		.build();
	// }
}
