package lookids.mono.notification.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.kafka.KafkaException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.notification.domain.FcmToken;
import lookids.mono.notification.domain.Notification;
import lookids.mono.notification.domain.NotificationType;
import lookids.mono.notification.dto.in.NotificationChattingRequestDto;
import lookids.mono.notification.dto.in.NotificationCommentReplyRequestDto;
import lookids.mono.notification.dto.in.NotificationCommentRequestDto;
import lookids.mono.notification.dto.in.NotificationFavoriteRequestDto;
import lookids.mono.notification.dto.in.NotificationFeedRequestDto;
import lookids.mono.notification.dto.in.NotificationFollowRequestDto;
import lookids.mono.notification.repository.FcmTokenRepository;
import lookids.mono.notification.repository.NotificationRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationKafkaListener {
	private final NotificationRepository notificationRepository;
	private final FcmTokenRepository fcmTokenRepository;
	private final NotificationSseService sseService;
	private final NotificationPushService pushService;

	@KafkaListener(topics = "${topic.feed.create}", groupId = "${consumer-group-id.feed}", concurrency = "50", containerFactory = "notificationFeedEventListenerContainerFactory")
	public void consumeFeedNotificationEvent(NotificationFeedRequestDto notificationFeedRequestDto) {
		log.info("consumeNotificationEvent: {}", notificationFeedRequestDto.getType().toUpperCase());
		log.info("consumeNotificationEvent: {}", notificationFeedRequestDto.getContent());
		try {
			// 알람 저장
			Notification notification = Notification.builder()
				.senderUuid(notificationFeedRequestDto.getSenderUuid())
				.receiverUuidList(notificationFeedRequestDto.getReceiverUuidList())
				.title("관심 친구가 게시글을 작성했습니다.")
				.content(notificationFeedRequestDto.getContent())
				.feedCode(notificationFeedRequestDto.getFeedCode())
				.mediaUrl(notificationFeedRequestDto.getMediaUrl())
				.type(NotificationType.valueOf(notificationFeedRequestDto.getType().toUpperCase()))
				.createdAt(LocalDateTime.now())
				.build();

			notificationRepository.save(notification);

			// 사용자에게 알림 전송
			notificationFeedRequestDto.getReceiverUuidList().forEach(receiverUuid -> {
				log.info("Sending notification to user: {}", receiverUuid);

				try {
					sseService.sendSseNotification(receiverUuid, notification); // SSE 알림 전송
					Optional<FcmToken> tmpFcm = fcmTokenRepository.findByUuid(receiverUuid);

					if (tmpFcm.isPresent()) {
						tmpFcm.get().getFcmTokenList().forEach(token -> {
							log.info("Sending push notification to token: {}", token);
							pushService.sendPushNotification(token, notification); // FCM 푸시 알림 전송
						});
					}
				} catch (Exception e) {
					log.error("Failed to send notification to user: {}", receiverUuid, e);
					throw new KafkaException("Notification processing failed", e);  // 예외 던져 Kafka 재시도 유도
				}
			});

		} catch (Exception e) {
			log.error("Failed to process feed notification event: {}", notificationFeedRequestDto, e);
			throw new KafkaException("Notification event processing failed", e);  // 전체 처리 실패 시
		}
	}

	@KafkaListener(topics = "${topic.chatting.create}", groupId = "${consumer-group-id.chatting}", containerFactory = "notificationChattingEventListenerContainerFactory")
	public void consumeChattingNotificationEvent(NotificationChattingRequestDto notificationChattingRequestDto) {

		String chatContent = notificationChattingRequestDto.getContent();

		log.info("consumeNotificationEvent: {}", notificationChattingRequestDto.getType().toUpperCase());
		log.info("consumeNotificationEvent: {}", chatContent);

		String splitedChatContent = chatContent.length() > 20 ? chatContent.substring(0, 20) + "..." : chatContent;

		// 알람 저장
		// 채팅 dto의 img 정보가 있다면 mediaUrl 필드 사용하고 없다면 미사용해서 document 생성
		Notification notification = Notification.builder()
			.senderUuid(notificationChattingRequestDto.getSenderUuid())
			.receiverUuidList(notificationChattingRequestDto.getReceiverUuidList()) // 1~N명의 채팅 유저가 존재할 수 있기에 list로 취급
			.roomId(notificationChattingRequestDto.getRoomId())
			//.title("메세지가 도착했습니다.")
			.content(splitedChatContent)
			.mediaUrl(notificationChattingRequestDto.getMediaUrl())
			.type(NotificationType.CHATTING)
			.createdAt(LocalDateTime.now())
			.build();

		notificationRepository.save(notification);

		// 정책: 빈도가 잦은 채팅은 sse 알림 미적용
		notification.getReceiverUuidList().forEach(receiverUuid -> {
			fcmTokenRepository.findByUuid(receiverUuid).get().getFcmTokenList().forEach(token -> {
				log.info("Sending push notification to user: {}", receiverUuid);
				log.info("Sending push notification to token: {}", token);
				pushService.sendPushNotification(token, notification); // FCM 푸시 알림 전송
			});
		});

	}

	@KafkaListener(topics = "${topic.feed.favorite}", groupId = "${consumer-group-id.favorite}", containerFactory = "notificationFavoriteEventListenerContainerFactory")
	public void consumeFeedFavoriteNotificationEvent(NotificationFavoriteRequestDto notificationFavoriteRequestDto) {
		log.info("consumeNotificationEvent: {}", notificationFavoriteRequestDto.getType().toUpperCase());
		log.info("consumeNotificationEvent: {}", "게시글 좋아요");

		// 알람 저장
		Notification notification = Notification.builder()
			.senderUuid(notificationFavoriteRequestDto.getSenderUuid())
			.feedCode(notificationFavoriteRequestDto.getFeedCode())
			.receiverUuidList(List.of(notificationFavoriteRequestDto.getReceiverUuid()))
			.feedCode(notificationFavoriteRequestDto.getFeedCode())
			.title("님이 게시글에 좋아요를 눌렀습니다")
			//.content("")
			.type(NotificationType.FAVORITE)
			.createdAt(LocalDateTime.now())
			.build();

		notificationRepository.save(notification);

		notification.getReceiverUuidList().forEach(receiverUuid -> {
			log.info("Sending notification to user: {}", receiverUuid);
			sseService.sendSseNotification(receiverUuid, notification); // SSE 알림 전송

			fcmTokenRepository.findByUuid(receiverUuid).get().getFcmTokenList().forEach(token -> {
				log.info("Sending push notification to user: {}", receiverUuid);
				log.info("Sending push notification to token: {}", token);
				pushService.sendPushNotification(token, notification); // FCM 푸시 알림 전송
			});
		});

	}

	@KafkaListener(topics = "${topic.comment.favorite}", groupId = "${consumer-group-id.favorite}", containerFactory = "notificationFavoriteEventListenerContainerFactory")
	public void consumeCommentFavoriteNotificationEvent(NotificationFavoriteRequestDto notificationFavoriteRequestDto) {
		log.info("consumeNotificationEvent: {}", notificationFavoriteRequestDto.getType().toUpperCase());
		log.info("consumeNotificationEvent: {}", "댓글 좋아요");

		// 알람 저장
		Notification notification = Notification.builder()
			.senderUuid(notificationFavoriteRequestDto.getSenderUuid())
			.receiverUuidList(List.of(notificationFavoriteRequestDto.getReceiverUuid()))
			.feedCode(notificationFavoriteRequestDto.getFeedCode())
			.title("님이 댓글에 좋아요를 눌렀습니다")
			//.content("")
			.type(NotificationType.FAVORITE)
			.createdAt(LocalDateTime.now())
			.build();

		notificationRepository.save(notification);

		notification.getReceiverUuidList().forEach(receiverUuid -> {
			log.info("Sending notification to user: {}", receiverUuid);
			sseService.sendSseNotification(receiverUuid, notification); // SSE 알림 전송

			fcmTokenRepository.findByUuid(receiverUuid).get().getFcmTokenList().forEach(token -> {
				log.info("Sending push notification to user: {}", receiverUuid);
				log.info("Sending push notification to token: {}", token);
				pushService.sendPushNotification(token, notification); // FCM 푸시 알림 전송
			});
		});

	}

	@KafkaListener(topics = "${topic.follow.create}", groupId = "${consumer-group-id.follow}", containerFactory = "notificationFollowEventListenerContainerFactory")
	public void consumeFollowNotificationEvent(NotificationFollowRequestDto notificationFollowRequestDto) {
		log.info("consumeNotificationEvent: {}", notificationFollowRequestDto.getType().toUpperCase());
		log.info("consumeNotificationEvent: {}", "팔로우");

		// 알람 저장
		Notification notification = Notification.builder()
			.senderUuid(notificationFollowRequestDto.getSenderUuid())
			.receiverUuidList(List.of(notificationFollowRequestDto.getReceiverUuid()))
			.title("님이 팔로우를 시작했습니다")
			//.content("")
			.type(NotificationType.FOLLOW)
			.createdAt(LocalDateTime.now())
			.build();

		notificationRepository.save(notification);

		notification.getReceiverUuidList().forEach(receiverUuid -> {
			log.info("Sending notification to user: {}", receiverUuid);
			sseService.sendSseNotification(receiverUuid, notification); // SSE 알림 전송

			fcmTokenRepository.findByUuid(receiverUuid).get().getFcmTokenList().forEach(token -> {
				log.info("Sending push notification to user: {}", receiverUuid);
				log.info("Sending push notification to token: {}", token);
				pushService.sendPushNotification(token, notification); // FCM 푸시 알림 전송
			});
		});

	}

	@KafkaListener(topics = "${topic.comment.create}", groupId = "${consumer-group-id.comment}", containerFactory = "notificationCommentEventListenerContainerFactory")
	public void consumeCommentNotificationEvent(NotificationCommentRequestDto notificationCommentRequestDto) {
		try {

			String commentContent = notificationCommentRequestDto.getContent();

			log.info("consumeNotificationEvent: {}", commentContent);

			String splitedCommentContent =
				commentContent.length() > 20 ? commentContent.substring(0, 20) + "..." : commentContent;

			// 알람 저장
			Notification notification = Notification.builder()
				.senderUuid(notificationCommentRequestDto.getFeedCode())
				.receiverUuidList(List.of(notificationCommentRequestDto.getReceiverUuid()))
				.feedCode(notificationCommentRequestDto.getFeedCode())
				.title("님이 댓글을 작성했습니다")
				.content(splitedCommentContent)
				.type(NotificationType.COMMENT)
				.createdAt(LocalDateTime.now())
				.build();

			notificationRepository.save(notification);

			notification.getReceiverUuidList().forEach(receiverUuid -> {
				log.info("Sending notification to user: {}", receiverUuid);
				try {
					sseService.sendSseNotification(receiverUuid, notification); // SSE 알림 전송

					Optional<FcmToken> tmpFcm = fcmTokenRepository.findByUuid(receiverUuid);

					if (tmpFcm.isPresent()) {
						tmpFcm.get().getFcmTokenList().forEach(token -> {
							log.info("Sending push notification to token: {}", token);
							pushService.sendPushNotification(token, notification); // FCM 푸시 알림 전송
						});
					}

				} catch (Exception e) {
					log.error("Failed to process comment notification event: {}", notificationCommentRequestDto, e);
					throw new KafkaException("Notification event processing failed", e);  // 전체 처리 실패 시
				}
			});
		} catch (Exception e) {
			log.error("Failed to process comment notification event: {}", notificationCommentRequestDto, e);
			throw new KafkaException("Notification event processing failed", e);  // 전체 처리 실패 시
		}

	}

	@KafkaListener(topics = "${topic.comment.reply.create}", groupId = "${consumer-group-id.comment-reply}", containerFactory = "notificationCommentReplyEventListenerContainerFactory")
	public void consumeCommentReplyNotificationEvent(
		NotificationCommentReplyRequestDto notificationCommentReplyRequestDto) {

		String replyCommentContent = notificationCommentReplyRequestDto.getContent();

		log.info("consumeNotificationEvent: {}", replyCommentContent);

		String splitedReplyCommentContent =
			replyCommentContent.length() > 20 ? replyCommentContent.substring(0, 20) + "..." : replyCommentContent;

		// 알람 저장
		Notification notification = Notification.builder()
			.senderUuid(notificationCommentReplyRequestDto.getFeedCode())
			.receiverUuidList(List.of(notificationCommentReplyRequestDto.getReceiverUuid()))
			.feedCode(notificationCommentReplyRequestDto.getFeedCode())
			.title("님이 댓글에 답글을 작성했습니다")
			.content(splitedReplyCommentContent)
			.type(NotificationType.COMMENT_REPLY)
			.createdAt(LocalDateTime.now())
			.build();

		log.info("splitedReplyCommentContent: {}", splitedReplyCommentContent);

		notificationRepository.save(notification);

		notification.getReceiverUuidList().forEach(receiverUuid -> {
			log.info("Sending notification to user: {}", receiverUuid);
			sseService.sendSseNotification(receiverUuid, notification); // SSE 알림 전송

			fcmTokenRepository.findByUuid(receiverUuid).get().getFcmTokenList().forEach(token -> {
				log.info("Sending push notification to user: {}", receiverUuid);
				log.info("Sending push notification to token: {}", token);
				pushService.sendPushNotification(token, notification); // FCM 푸시 알림 전송
			});
		});
	}

}
