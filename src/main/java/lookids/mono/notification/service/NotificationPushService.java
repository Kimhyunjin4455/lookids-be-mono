package lookids.mono.notification.service;

import lookids.mono.notification.domain.Notification;
import lookids.mono.notification.dto.in.FcmTokenRequestDto;

public interface NotificationPushService {
	void sendPushNotification(String token, Notification notification);

	void createFcmToken(FcmTokenRequestDto fcmTokenRequestDto);

	void deleteFcmToken(String uuid, String fcmToken);
}
