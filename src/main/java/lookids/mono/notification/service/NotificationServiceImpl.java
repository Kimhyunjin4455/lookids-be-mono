package lookids.mono.notification.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.notification.domain.Notification;
import lookids.mono.notification.dto.out.NotificationResponseDto;
import lookids.mono.notification.repository.NotificationRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {
	private final NotificationRepository notificationRepository;

	@Override
	public NotificationResponseDto readAlarm(String senderUuid) {
		// MongoDB에서 senderUuid에 해당하는 단일 알림을 조회
		Notification notification = notificationRepository.findBySenderUuid(senderUuid);
		log.info("notification: {}", notification.getContent());
		log.info("notification: {}", notification.getCreatedAt());

		// 조회된 알림을 NotificationResponseDto로 변환하여 반환
		if (notification != null) {
			return NotificationResponseDto.toDto(notification);
		} else {
			// 알림이 없을 경우 적절한 처리를 할 수 있음 (예: 예외 처리)
			return null;
		}
	}

}
