package lookids.mono.notification.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import lookids.mono.notification.domain.Notification;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
	// todo: 보상 로직 -> type, sender, content 을 통해 저장된 알림 조회 후 재활용에 대해서 추후 작성
	Notification findBySenderUuid(String senderUuid);
}
