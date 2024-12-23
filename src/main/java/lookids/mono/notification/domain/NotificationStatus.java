package lookids.alarm.notification.domain;

public enum NotificationStatus {
	PENDING, // 대기 상태
	SENT, // 발송 상태
	FAILED_TEMPORARY, // 일시적 실패
	FAILED_PERMANENT // 영구적 실패
}
