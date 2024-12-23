package lookids.manager.event.application;

import java.util.List;

import lookids.manager.event.domain.Event;
import lookids.manager.event.dto.in.EventRequestDto;
import lookids.manager.event.dto.in.EventUpdateRequestDto;
import lookids.manager.event.dto.out.EventResponseDto;
import lookids.manager.event.vo.out.EventResponseVo;

public interface EventService {

	void createEvent(EventRequestDto eventRequestDto);
	EventResponseDto readEvent(String eventCode);
	List<EventResponseDto> readGoingEventList();
	List<EventResponseDto> readExpiredEventList();
	void updateEvent(String eventCode, EventUpdateRequestDto eventUpdateRequestDto);
	void deleteEvent(String eventCode);
}