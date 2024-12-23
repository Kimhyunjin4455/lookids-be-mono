package com.lookids.event.event.application;

import java.util.List;

import com.lookids.event.event.dto.EventRequestDto;
import com.lookids.event.event.dto.EventResponseDto;

public interface EventService {
	void createEvent(EventRequestDto eventRequestDto);

	void updateEvent(EventRequestDto eventRequestDto);

	void deleteEvent(String eventCode);

	EventResponseDto readEvent(String eventCode);

	List<EventResponseDto> readActiveEventList();

	List<EventResponseDto> readDeactivateEventList();
}
