package lookids.manager.event.application;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lookids.manager.common.entity.BaseResponseStatus;
import lookids.manager.common.exception.BaseException;
import lookids.manager.event.domain.Event;
import lookids.manager.event.dto.in.EventRequestDto;
import lookids.manager.event.dto.in.EventUpdateRequestDto;
import lookids.manager.event.dto.out.EventResponseDto;
import lookids.manager.event.infrastructure.EventRepository;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService{

	private final EventRepository eventRepository;

	@Override
	public void createEvent(EventRequestDto eventRequestDto) {
		String eventCode;
		eventCode = UUID.randomUUID().toString();
		eventRepository.save(eventRequestDto.toEntity(eventCode));
	}

	@Override
	public EventResponseDto readEvent(String eventCode) {
		return EventResponseDto.toDto(eventRepository.findByEventCode(eventCode)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_EVENT)));
	}

	@Override
	public List<EventResponseDto> readGoingEventList() {
		LocalDateTime currentTime = LocalDateTime.now();
		return eventRepository.findByExpiredAtAfter(currentTime).stream()
			.map(EventResponseDto::toDto)
			.toList();
	}

	@Override
	public List<EventResponseDto> readExpiredEventList() {
		LocalDateTime currentTime = LocalDateTime.now();
		return eventRepository.findByExpiredAtBefore(currentTime).stream()
			.map(EventResponseDto::toDto)
			.toList();
	}

	@Override
	public void updateEvent(String eventCode, EventUpdateRequestDto eventUpdateRequestDto) {
		Event event = eventRepository.findByEventCode(eventCode)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_EVENT));
		event.update(eventUpdateRequestDto);
		eventRepository.save(event);
	}

	@Override
	public void deleteEvent(String eventCode) {
		Event event = eventRepository.findByEventCode(eventCode)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_EVENT));
		eventRepository.deleteByEventCode(eventCode);
	}

}
