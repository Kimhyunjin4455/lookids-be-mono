package lookids.mono.chatting.dto.out;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.mono.chatting.domain.ChatRoom;
import lookids.mono.chatting.domain.Participant;
import lookids.mono.chatting.vo.out.ParticipantListVo;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ParticipantListDto {
	private String roomId;
	private String roomName;
	private List<String> participants;

	public static ParticipantListDto toDto(ChatRoom chatRoom) {
		List<String> participantIds = chatRoom.getParticipants().stream().map(Participant::getUserId).toList();

		return ParticipantListDto.builder()
			.roomId(chatRoom.getId())
			.roomName(chatRoom.getRoomName())
			.participants(participantIds)
			.build();
	}

	public ParticipantListVo toVo() {
		return ParticipantListVo.builder().roomId(roomId).roomName(roomName).participants(participants).build();
	}
}
