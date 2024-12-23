package lookids.alarm.notification.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import lookids.alarm.notification.domain.Notification;

public interface NotificationSseService {
	public SseEmitter registerClient(String userInfo);
	public void sendSseNotification(String userInfo, Notification notification);
	public SseEmitter readExistingEmitter(String userInfo);
}
