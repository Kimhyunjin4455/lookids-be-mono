package lookids.mono.notification.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lookids.mono.notification.domain.Notification;

public interface NotificationSseService {
	SseEmitter registerClient(String userInfo);

	void sendSseNotification(String userInfo, Notification notification);

	SseEmitter readExistingEmitter(String userInfo);
}
