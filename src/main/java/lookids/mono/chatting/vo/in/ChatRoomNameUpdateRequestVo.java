package lookids.mono.chatting.vo.in;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ChatRoomNameUpdateRequestVo {
	private String roomId;
	private String roomName;
}
