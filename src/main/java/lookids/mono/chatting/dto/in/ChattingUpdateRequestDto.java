package lookids.chatting.chatting.dto.in;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.chatting.chatting.domain.ChatMessage;
import lookids.chatting.chatting.domain.MessageType;
import lookids.chatting.chatting.vo.in.ChattingUpdateRequestVo;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChattingUpdateRequestDto {
	private String id;
	private String roomId;
	private MessageType messageType;
	private String message;
	private String senderId;

	public static ChattingUpdateRequestDto toDto(ChattingUpdateRequestVo chattingUpdateRequestVo) {
		return ChattingUpdateRequestDto.builder()
			.id(chattingUpdateRequestVo.getId())
			.roomId(chattingUpdateRequestVo.getRoomId())
			.messageType(chattingUpdateRequestVo.getMessageType())
			.message(chattingUpdateRequestVo.getMessage())
			.senderId(chattingUpdateRequestVo.getSenderId())
			.build();
	}

	public ChatMessage toEntity(ChatMessage chatMessage) {
		return ChatMessage.builder()
			.id(chatMessage.getId())
			.roomId(chatMessage.getRoomId())
			.messageType(messageType)
			.message(message)
			.senderId(chatMessage.getSenderId())
			.createdAt(chatMessage.getCreatedAt())
			.updatedAt(LocalDateTime.now())
			.build();
	}
}
