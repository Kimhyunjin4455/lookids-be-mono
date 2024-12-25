package lookids.mono.notification.service;

import lookids.mono.notification.dto.out.NotificationResponseDto;

public interface NotificationService {
	// void createAlarm(NotificationFeedRequestDto alarmRequestDto);
	NotificationResponseDto readAlarm(String receiverUuid);

}
