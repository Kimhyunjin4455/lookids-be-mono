package lookids.mono.chatting.dto.out;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.bson.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.mono.chatting.domain.ChatMessage;
import lookids.mono.chatting.domain.MessageType;
import lookids.mono.chatting.vo.out.ChattingResponseVo;

@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class ChattingResponseDto {
	private String id;
	private String roomId;
	private MessageType messageType;
	private String message;
	private String senderId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public static ChattingResponseDto toDtoFromDocument(Document document) {
		return ChattingResponseDto.builder()
			.id(document.getObjectId("_id").toHexString()) // MongoDB ObjectId 처리
			.roomId(document.getString("roomId"))
			.messageType(MessageType.valueOf(document.getString("messageType")))
			.message(document.getString("message"))
			.senderId(document.getString("senderId"))
			.createdAt(LocalDateTime.ofInstant(document.getDate("createdAt").toInstant(), ZoneId.of("Asia/Seoul")))
			.updatedAt(LocalDateTime.ofInstant(document.getDate("updatedAt").toInstant(), ZoneId.of("Asia/Seoul")))
			.build();
	}

	public static ChattingResponseDto toDto(ChatMessage chatMessage) {
		return ChattingResponseDto.builder()
			.id(chatMessage.getId())
			.roomId(chatMessage.getRoomId())
			.messageType(chatMessage.getMessageType())
			.message(chatMessage.getMessage())
			.senderId(chatMessage.getSenderId())

			.createdAt(chatMessage.getCreatedAt()
				.atZone(ZoneId.systemDefault())
				.withZoneSameInstant(ZoneId.of("Asia/Seoul"))
				.toLocalDateTime())
			.updatedAt(chatMessage.getUpdatedAt()
				.atZone(ZoneId.systemDefault())
				.withZoneSameInstant(ZoneId.of("Asia/Seoul"))
				.toLocalDateTime())

			.build();
	}

	public ChattingResponseVo toVo() {
		return ChattingResponseVo.builder()
			.id(id)
			.roomId(roomId)
			.messageType(messageType)
			.message(message)
			.senderId(senderId)
			.createdAt(createdAt)
			.updatedAt(updatedAt)
			.build();
	}
}
