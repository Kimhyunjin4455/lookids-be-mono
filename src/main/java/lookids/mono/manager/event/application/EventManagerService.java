package lookids.mono.manager.event.application;

import java.util.List;

import lookids.mono.manager.event.dto.in.EventRequestDto;
import lookids.mono.manager.event.dto.in.EventUpdateRequestDto;
import lookids.mono.manager.event.dto.out.EventResponseDto;

public interface EventManagerService {

	void createEvent(EventRequestDto eventRequestDto);

	EventResponseDto readEvent(String eventCode);

	List<EventResponseDto> readGoingEventList();

	List<EventResponseDto> readExpiredEventList();

	void updateEvent(String eventCode, EventUpdateRequestDto eventUpdateRequestDto);

	void deleteEvent(String eventCode);
}