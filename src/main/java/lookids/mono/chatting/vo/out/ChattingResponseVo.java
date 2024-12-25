package lookids.mono.chatting.vo.out;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.mono.chatting.domain.MessageType;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChattingResponseVo {
	private String id;
	private String roomId;
	private MessageType messageType;
	private String message;
	private String senderId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
