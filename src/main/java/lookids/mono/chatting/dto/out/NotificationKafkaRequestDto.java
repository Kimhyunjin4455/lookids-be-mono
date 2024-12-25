package lookids.mono.chatting.dto.out;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lookids.mono.chatting.domain.ChatMessage;
import lookids.mono.chatting.domain.MessageType;

@Getter
public class NotificationKafkaRequestDto {

	private String senderUuid;
	private String roomId;
	private List<String> receiverUuidList;
	private String content;
	private String mediaUrl;

	@Builder
	public NotificationKafkaRequestDto(String senderUuid, String roomId, List<String> receiverUuidList, String content,
		String mediaUrl) {
		this.senderUuid = senderUuid;
		this.roomId = roomId;
		this.receiverUuidList = receiverUuidList;
		this.content = content;
		this.mediaUrl = mediaUrl;
	}

	public static NotificationKafkaRequestDto toDto(ChatMessage chatMessage, List<String> participants) {
		// 메시지 내용 설정
		String content = chatMessage.getMessageType() == MessageType.IMAGE ? "이미지를 보냈습니다." : chatMessage.getMessage();

		String mediaUrl = chatMessage.getMessageType() == MessageType.IMAGE ? chatMessage.getMessage() : null;

		return NotificationKafkaRequestDto.builder()
			.senderUuid(chatMessage.getSenderId())
			.receiverUuidList(participants)
			.roomId(chatMessage.getRoomId())
			.content(content)
			.mediaUrl(mediaUrl)
			.build();
	}
}