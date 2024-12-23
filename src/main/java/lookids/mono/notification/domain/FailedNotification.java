package lookids.alarm.notification.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class FailedNotification {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String failedRequestData;  // 실패한 요청 데이터 JSON 문자열
	private String failedRequestType;  // 실패한 요청 타입
	private String exceptionMessage;   // 예외 메시지
	private LocalDateTime failedAt;    // 실패 발생 시간

	@Enumerated(EnumType.STRING)
	private NotificationStatus status; // FAILED_PERMANENT 등

	@Builder
	public FailedNotification(
		String failedRequestData,
		String failedRequestType,
		String exceptionMessage,
		LocalDateTime failedAt,
		NotificationStatus status
	) {
		this.failedRequestData = failedRequestData;
		this.failedRequestType = failedRequestType;
		this.exceptionMessage = exceptionMessage;
		this.failedAt = failedAt;
		this.status = status;
	}
}
