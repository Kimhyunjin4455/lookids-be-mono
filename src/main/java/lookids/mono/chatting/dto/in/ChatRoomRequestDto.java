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
import lookids.mono.chatting.vo.in.ChatRoomRequestVo;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomRequestDto {
	private String roomName;
	private List<Participant> participants;

	public static ChatRoomRequestDto toDto(ChatRoomRequestVo chatRoomRequestVo) {
		return ChatRoomRequestDto.builder()
			.roomName(chatRoomRequestVo.getRoomName())
			.participants(chatRoomRequestVo.getParticipants()
				.stream()
				.map(vo -> Participant.builder()
					.userId(vo.getUserId())
					.unreadCount(0L)
					.isOnline(false)
					.lastReadChatMessage(null)
					.lastEnterTime(LocalDateTime.now())
					.lastLeaveTime(LocalDateTime.now())
					.build())
				.toList())
			.build();
	}

	public ChatRoom toEntity() {
		return ChatRoom.builder().roomName(roomName).participants(participants).build();
	}

}
