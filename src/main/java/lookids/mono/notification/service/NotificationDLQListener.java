package lookids.alarm.notification.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.alarm.notification.domain.FailedNotification;
import lookids.alarm.notification.domain.FcmToken;
import lookids.alarm.notification.domain.Notification;
import lookids.alarm.notification.domain.NotificationStatus;
import lookids.alarm.notification.domain.NotificationType;
import lookids.alarm.notification.dto.in.NotificationFeedRequestDto;
import lookids.alarm.notification.repository.FailedNotificationRepository;
import lookids.alarm.notification.repository.FcmTokenRepository;
import lookids.alarm.notification.repository.NotificationRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationDLQListener {
	private final NotificationRepository notificationRepository;
	private final FcmTokenRepository fcmTokenRepository;
	private final KafkaTemplate<String, NotificationFeedRequestDto> kafkaFeedTemplate;
	private final NotificationSseService sseService;
	private final NotificationPushService pushService;
	private final FailedNotificationRepository failedNotificationRepository;

	@Value("${topic.feed.create}")
	private String feedCreateTopic;


	@KafkaListener(
		topics = "${topic.feed.create}-dead-letter",
		groupId = "${consumer-group-id.feed}-dlq"
	)public void handleDeadLetter(NotificationFeedRequestDto notificationFeedRequestDto) {
		log.info("handleDeadLetter: {}", notificationFeedRequestDto);

		try {
			// 재시도 로직 실행
			processNotification(notificationFeedRequestDto);
			log.info("Successfully processed message from DLQ: {}", notificationFeedRequestDto);

		} catch (Exception e) {
			log.error("Failed to process message from DLQ: {}", notificationFeedRequestDto, e);

			// 실패한 알림 저장
			FailedNotification failedNotification = FailedNotification.builder()
				.failedRequestData(notificationFeedRequestDto.toString())
				.failedRequestType(notificationFeedRequestDto.getType())
				.exceptionMessage(e.getMessage())
				.failedAt(LocalDateTime.now())
				.status(NotificationStatus.FAILED_PERMANENT)
				.build();

			failedNotificationRepository.save(failedNotification);
		}

	}

	private void processNotification(NotificationFeedRequestDto notificationFeedRequestDto) {
		Notification notification = Notification.builder()
			.senderUuid(notificationFeedRequestDto.getSenderUuid())
			.receiverUuidList(notificationFeedRequestDto.getReceiverUuidList())
			.title("님이 게시글을 작성했습니다.")
			.content(notificationFeedRequestDto.getContent())
			.feedCode(notificationFeedRequestDto.getFeedCode())
			.mediaUrl(notificationFeedRequestDto.getMediaUrl())
			.type(NotificationType.valueOf(notificationFeedRequestDto.getType().toUpperCase()))
			.createdAt(LocalDateTime.now())
			.build();

		// 알림 저장
		notificationRepository.save(notification);

		notificationFeedRequestDto.getReceiverUuidList().forEach(receiverUuid -> {
			try {
				// SSE 전송
				sseService.sendSseNotification(receiverUuid, notification);

				// FCM 토큰 확인 및 푸시 전송
				Optional<FcmToken> fcmTokenOpt = fcmTokenRepository.findByUuid(receiverUuid);
				if (fcmTokenOpt.isPresent()) {
					fcmTokenOpt.get().getFcmTokenList().forEach(token ->
						pushService.sendPushNotification(token, notification)
					);
				} else {
					throw new IllegalArgumentException("FCM 토큰이 없습니다.");
				}
			} catch (Exception e) {
				log.error("알림 전송 실패 - 사용자: {}, 오류: {}", receiverUuid, e.getMessage());
				throw e;  // 재시도 유도
			}
		});
	}

}
