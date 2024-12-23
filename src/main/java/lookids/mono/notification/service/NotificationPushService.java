package lookids.alarm.notification.service;

import lookids.alarm.notification.domain.Notification;
import lookids.alarm.notification.dto.in.FcmTokenRequestDto;

public interface NotificationPushService {
	void sendPushNotification(String token, Notification notification);
	void createFcmToken(FcmTokenRequestDto fcmTokenRequestDto);
	void deleteFcmToken(String uuid, String fcmToken);
}
