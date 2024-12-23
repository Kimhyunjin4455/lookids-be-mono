package lookids.chatting.chatting.dto.in;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.chatting.chatting.domain.ChatRoom;
import lookids.chatting.chatting.domain.Participant;
import lookids.chatting.chatting.vo.in.ChatRoomNameUpdateRequestVo;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
public class ChatRoomNameUpdateRequestDto {
	private String id;
	private String roomName;
	private List<Participant> participants;
	private String lastChatMessageAt;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public static ChatRoomNameUpdateRequestDto toDto(ChatRoomNameUpdateRequestVo chatRoomNameUpdateRequestVo) {
		return ChatRoomNameUpdateRequestDto.builder()
			.id(chatRoomNameUpdateRequestVo.getRoomId())
			.roomName(chatRoomNameUpdateRequestVo.getRoomName())
			.build();
	}

	public static ChatRoom toEntity(ChatRoom chatRoom, String roomName) {
		return ChatRoom.builder()
			.id(chatRoom.getId())
			.roomName(roomName)
			.participants(chatRoom.getParticipants())
			.lastChatMessageAt(chatRoom.getLastChatMessageAt())
			.createdAt(chatRoom.getCreatedAt())
			.updatedAt(chatRoom.getUpdatedAt())
			.build();
	}
}
