package com.lookids.event.event.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lookids.event.common.entity.BaseResponseStatus;
import com.lookids.event.common.exception.BaseException;
import com.lookids.event.event.domain.Event;
import com.lookids.event.event.dto.EventRequestDto;
import com.lookids.event.event.dto.EventResponseDto;
import com.lookids.event.event.infrastructure.EventRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
	private final EventRepository eventRepository;

	@Override
	public void createEvent(EventRequestDto eventRequestDto) {
		eventRepository.save(eventRequestDto.toEntity());
	}

	@Override
	public void updateEvent(EventRequestDto eventRequestDto) {

		Event event = eventRepository.findByEventCode(eventRequestDto.getEventCode())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_EVENT));

		eventRepository.save(eventRequestDto.updateEntity(event));
	}

	@Override
	public void deleteEvent(String eventCode) {
		Event event = eventRepository.findByEventCode(eventCode)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_EVENT));

		eventRepository.save(EventRequestDto.deleteEntity(event));
	}

	@Override
	public EventResponseDto readEvent(String eventCode) {
		Event event = eventRepository.findByEventCode(eventCode)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_EVENT));

		return EventResponseDto.toDto(event);
	}

	@Override
	public List<EventResponseDto> readActiveEventList() {
		return eventRepository.findByStateTrueOrderByStartedAtAsc()
			.stream()
			.map(EventResponseDto::toDto)
			.toList();
	}

	@Override
	public List<EventResponseDto> readDeactivateEventList() {
		return eventRepository.findByStateFalseOrderByStartedAtAsc()
			.stream()
			.map(EventResponseDto::toDto)
			.toList();
	}

}
