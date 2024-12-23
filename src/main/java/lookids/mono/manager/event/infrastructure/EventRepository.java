package lookids.manager.event.infrastructure;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lookids.manager.event.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

	Optional<Event> findByEventCode(String eventCode);
	List<Event> findByExpiredAtAfter(LocalDateTime currentTime);
	List<Event> findByExpiredAtBefore(LocalDateTime currentTime);
	void deleteByEventCode(String eventCode);
}
