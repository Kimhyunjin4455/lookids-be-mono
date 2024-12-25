package lookids.mono.event.event.application;

import java.util.List;

import lookids.mono.event.event.dto.EventRequestDto;
import lookids.mono.event.event.dto.EventResponseDto;

public interface EventService {
	void createEvent(EventRequestDto eventRequestDto);

	void updateEvent(EventRequestDto eventRequestDto);

	void deleteEvent(String eventCode);

	EventResponseDto readEvent(String eventCode);

	List<EventResponseDto> readActiveEventList();

	List<EventResponseDto> readDeactivateEventList();
}
