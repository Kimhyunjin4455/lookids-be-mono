package lookids.mono.chatting.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@Builder
@ToString
@AllArgsConstructor
public class Participant {

	private String userId;
	private Long unreadCount;
	private String lastReadChatMessage;
	private Boolean isOnline;
	private LocalDateTime lastEnterTime;
	private LocalDateTime lastLeaveTime;

	public void updateLastEnterTime(LocalDateTime time) {
		this.lastEnterTime = time;
		this.isOnline = true;
		this.unreadCount = 0L; // 들어올 때 읽지 않은 메시지 수를 초기화
	}

	public void updateLastLeaveTime(LocalDateTime time) {
		this.lastLeaveTime = time;
		this.isOnline = false;
		this.unreadCount = 0L; // 들어올 때 읽지 않은 메시지 수를 초기화
	}

	public void updateLastReadChatMessage(String id) {
		this.lastReadChatMessage = id;
	}

	public void incrementUnreadCount() {
		this.unreadCount += 1; // 새로운 메시지 도착 시 1씩 증가
	}

}
