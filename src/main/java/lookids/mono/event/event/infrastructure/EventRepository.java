package com.lookids.event.event.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lookids.event.event.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
	Optional<Event> findByEventCode(String eventCode);

	List<Event> findByStateTrueOrderByStartedAtAsc();

	List<Event> findByStateFalseOrderByStartedAtAsc();
}
