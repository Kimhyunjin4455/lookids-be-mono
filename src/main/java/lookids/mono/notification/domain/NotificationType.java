package lookids.alarm.notification.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotificationType {
	CHATTING("채팅"),
	FEED("피드"),
	FAVORITE("좋아요"),
	NOTICE("공지"),
	COMMENT("댓글"),
	COMMENT_REPLY("대댓글"),
	FOLLOW("팔로우");

	private final String notificationType;

	@JsonValue
	public String getNotificationType() {
		return notificationType;
	}

	@JsonCreator
	public static NotificationType fromString(String alarmType) {
		for (NotificationType type : NotificationType.values()) {
			if (type.getNotificationType().equals(alarmType)) {
				return type;
			}
		}
		throw new IllegalArgumentException("알 수 없는 타입입니다. "+alarmType);
	}



}
