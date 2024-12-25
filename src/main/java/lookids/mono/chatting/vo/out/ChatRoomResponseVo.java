package lookids.mono.chatting.vo.out;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatRoomResponseVo {
	private String roomId;
	private String roomName;
	private Long unreadCount;
	private List<String> participants; // Participant 리스트 추가
	private String lastChatMessageAt;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
