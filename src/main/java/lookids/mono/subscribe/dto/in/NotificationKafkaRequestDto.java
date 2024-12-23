package lookids.subscribe.subscribe.dto.in;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotificationKafkaRequestDto {
	private String senderUuid;
	private List<String> receiverUuidList;
	private String feedCode;
	private String content;
	private String mediaUrl;
	private String type;

	@Builder
	public NotificationKafkaRequestDto(
		String senderUuid,
		List<String> receiverUuidList,
		String feedCode,
		String content,
		String mediaUrl,
		String type
	) {
		this.senderUuid = senderUuid;
		this.receiverUuidList = receiverUuidList;
		this.feedCode = feedCode;
		this.content = content;
		this.mediaUrl = mediaUrl;
		this.type = type;
	}

	public static NotificationKafkaRequestDto toDto(FeedKafkaRequestDto kafkaFeedRequestDto, List<String> receiverUuidList, String splitedContent, String type) {
		return NotificationKafkaRequestDto.builder()
			.senderUuid(kafkaFeedRequestDto.getUuid())
			.receiverUuidList(receiverUuidList)
			.feedCode(kafkaFeedRequestDto.getFeedCode())
			.content(splitedContent)
			.mediaUrl(kafkaFeedRequestDto.getMediaUrlList().get(0))
			.type(type)
			.build();

	}
}
