package lookids.chatting.chatting.vo.in;

import lombok.Getter;
import lombok.ToString;
import lookids.chatting.chatting.domain.MessageType;

@ToString
@Getter
public class ChattingUpdateRequestVo {
	private String id;
	private String roomId;
	private MessageType messageType;
	private String message;
	private String senderId;
}
