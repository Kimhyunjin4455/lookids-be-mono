package lookids.mono.chatting.vo.in;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ChatRoomRequestVo {
	private String roomName;
	private List<ParticipantRequestVo> participants;
}


