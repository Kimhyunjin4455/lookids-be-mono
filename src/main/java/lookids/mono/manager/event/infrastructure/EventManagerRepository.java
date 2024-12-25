package lookids.mono.manager.event.infrastructure;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lookids.mono.manager.event.domain.EventManager;

public interface EventManagerRepository extends JpaRepository<EventManager, Long> {

	Optional<EventManager> findByEventCode(String eventCode);

	List<EventManager> findByExpiredAtAfter(LocalDateTime currentTime);

	List<EventManager> findByExpiredAtBefore(LocalDateTime currentTime);

	void deleteByEventCode(String eventCode);
}
