package lookids.mono.manager.event.application;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lookids.mono.common.entity.BaseResponseStatus;
import lookids.mono.common.exception.BaseException;
import lookids.mono.manager.event.domain.Event;
import lookids.mono.manager.event.dto.in.EventRequestDto;
import lookids.mono.manager.event.dto.in.EventUpdateRequestDto;
import lookids.mono.manager.event.dto.out.EventResponseDto;
import lookids.mono.manager.event.infrastructure.EventRepository;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

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
		return eventRepository.findByExpiredAtAfter(currentTime).stream().map(EventResponseDto::toDto).toList();
	}

	@Override
	public List<EventResponseDto> readExpiredEventList() {
		LocalDateTime currentTime = LocalDateTime.now();
		return eventRepository.findByExpiredAtBefore(currentTime).stream().map(EventResponseDto::toDto).toList();
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
