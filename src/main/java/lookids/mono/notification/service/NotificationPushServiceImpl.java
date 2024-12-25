package lookids.mono.notification.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.notification.domain.FcmToken;
import lookids.mono.notification.domain.Notification;
import lookids.mono.notification.dto.in.FcmTokenRequestDto;
import lookids.mono.notification.repository.FcmTokenRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationPushServiceImpl implements NotificationPushService {

	private final FcmTokenRepository fcmTokenRepository;
	private final FirebaseMessaging firebaseMessaging;

	@Override
	public void sendPushNotification(String token, Notification notification) {
		// com.google.firebase.messaging.Notification 은 Builder 클래스에 대한 public 접근을 허용하지 않지만, Notification.builder()라는 정적 메서드를 제공
		com.google.firebase.messaging.Notification fcmNotification = com.google.firebase.messaging.Notification.builder() // builder() 메서드 사용
			.setTitle(notification.getTitle())  // 제목 설정
			.setBody(notification.getContent()) // 내용 설정
			.build(); // 빌드하여 Notification 객체 생성

		Message message = Message.builder().setToken(token).setNotification(fcmNotification).build();

		try {
			String response = firebaseMessaging.send(message);
			log.info("FCM Feed Notification sent: {}", response);
		} catch (FirebaseMessagingException e) {
			log.error("FCM Feed Notification Error: {}", e.getMessage());
		}
	}

	@Override
	public void createFcmToken(FcmTokenRequestDto fcmTokenRequestDto) {
		// MongoDB에서 UUID로 기존 문서 조회
		Optional<FcmToken> optionalFcmToken = fcmTokenRepository.findByUuid(fcmTokenRequestDto.getUuid());
		FcmToken fcmToken;

		if (optionalFcmToken.isPresent()) {
			// 기존 문서 수정
			fcmToken = optionalFcmToken.get();
			List<String> tokenList = fcmToken.getFcmTokenList();
			if (!tokenList.contains(fcmTokenRequestDto.getFcmToken())) {
				tokenList.add(fcmTokenRequestDto.getFcmToken());
			}
		} else {
			// 새로운 문서 생성
			fcmToken = FcmToken.builder()
				.uuid(fcmTokenRequestDto.getUuid())
				.fcmTokenList(new ArrayList<>(List.of(fcmTokenRequestDto.getFcmToken())))
				.build();
		}

		// 저장 (MongoDB가 ID 유무에 따라 새로 생성하거나 업데이트함)
		fcmTokenRepository.save(fcmToken);
	}

	@Override
	public void deleteFcmToken(String uuid, String fcmToken) {
		// MongoDB에서 UUID로 기존 문서 조회
		FcmToken fcmTokenDocument = fcmTokenRepository.findByUuid(uuid).get();

		if (fcmTokenDocument != null) {
			// 기존 문서에서 FCM 토큰 삭제
			List<String> tokenList = fcmTokenDocument.getFcmTokenList();
			if (tokenList.contains(fcmToken)) {
				tokenList.remove(fcmToken);  // 해당 토큰을 리스트에서 삭제
				// 업데이트된 FCM 토큰 리스트 저장
				fcmTokenRepository.save(fcmTokenDocument);
			}
		}
	}
}
