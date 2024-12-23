package lookids.chatting.chatting.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.chatting.chatting.domain.ChatMessage;
import lookids.chatting.chatting.domain.MessageType;
import lookids.chatting.chatting.vo.in.ChattingRequestVo;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChattingRequestDto {
	private String roomId;
	private MessageType messageType;
	private String message;
	private String senderId;

	public static ChattingRequestDto toDto(ChattingRequestVo chattingRequestVo) {
		return ChattingRequestDto.builder()
			.roomId(chattingRequestVo.getRoomId())
			.messageType(chattingRequestVo.getMessageType())
			.message(chattingRequestVo.getMessage())
			.senderId(chattingRequestVo.getSenderId())
			.build();
	}

	public ChatMessage toEntity() {
		return ChatMessage.builder()
			.roomId(roomId)
			.messageType(messageType)
			.message(message)
			.senderId(senderId)
			.build();
	}
}
