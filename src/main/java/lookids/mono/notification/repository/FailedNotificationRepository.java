package lookids.mono.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lookids.mono.notification.domain.FailedNotification;

@Repository
public interface FailedNotificationRepository extends JpaRepository<FailedNotification, Long> {
}
