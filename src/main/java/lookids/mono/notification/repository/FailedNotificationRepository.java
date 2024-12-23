package lookids.alarm.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lookids.alarm.notification.domain.FailedNotification;

@Repository
public interface FailedNotificationRepository extends JpaRepository<FailedNotification, Long> {
}
