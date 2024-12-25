package lookids.mono.chatting.dto.in;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.mono.chatting.domain.ChatRoom;
import lookids.mono.chatting.domain.Participant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ChatRoomUpdateRequestDto {
	private String id;
	private String roomName;
	private List<Participant> participants;
	private String lastChatMessageAt;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public static ChatRoom toUpdateEntity(ChatRoom chatRoom, String message) {
		return ChatRoom.builder()
			.id(chatRoom.getId())
			.roomName(chatRoom.getRoomName())
			.participants(chatRoom.getParticipants())
			.lastChatMessageAt(message)
			.createdAt(chatRoom.getCreatedAt())
			.updatedAt(chatRoom.getUpdatedAt())
			.build();
	}
}
