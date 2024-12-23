package lookids.alarm.notification.service;

import lookids.alarm.notification.dto.out.NotificationResponseDto;

public interface NotificationService {
	// void createAlarm(NotificationFeedRequestDto alarmRequestDto);
	NotificationResponseDto readAlarm(String receiverUuid);

}
