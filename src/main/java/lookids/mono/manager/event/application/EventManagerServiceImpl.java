package lookids.mono.manager.event.application;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lookids.mono.common.entity.BaseResponseStatus;
import lookids.mono.common.exception.BaseException;
import lookids.mono.manager.event.domain.EventManager;
import lookids.mono.manager.event.dto.in.EventRequestDto;
import lookids.mono.manager.event.dto.in.EventUpdateRequestDto;
import lookids.mono.manager.event.dto.out.EventResponseDto;
import lookids.mono.manager.event.infrastructure.EventManagerRepository;

@Service
@RequiredArgsConstructor
public class EventManagerServiceImpl implements EventManagerService {

	private final EventManagerRepository eventManagerRepository;

	@Override
	public void createEvent(EventRequestDto eventRequestDto) {
		String eventCode;
		eventCode = UUID.randomUUID().toString();
		eventManagerRepository.save(eventRequestDto.toEntity(eventCode));
	}

	@Override
	public EventResponseDto readEvent(String eventCode) {
		return EventResponseDto.toDto(eventManagerRepository.findByEventCode(eventCode)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_EVENT)));
	}

	@Override
	public List<EventResponseDto> readGoingEventList() {
		LocalDateTime currentTime = LocalDateTime.now();
		return eventManagerRepository.findByExpiredAtAfter(currentTime).stream().map(EventResponseDto::toDto).toList();
	}

	@Override
	public List<EventResponseDto> readExpiredEventList() {
		LocalDateTime currentTime = LocalDateTime.now();
		return eventManagerRepository.findByExpiredAtBefore(currentTime).stream().map(EventResponseDto::toDto).toList();
	}

	@Override
	public void updateEvent(String eventCode, EventUpdateRequestDto eventUpdateRequestDto) {
		EventManager eventManager = eventManagerRepository.findByEventCode(eventCode)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_EVENT));
		eventManager.update(eventUpdateRequestDto);
		eventManagerRepository.save(eventManager);
	}

	@Override
	public void deleteEvent(String eventCode) {
		EventManager eventManager = eventManagerRepository.findByEventCode(eventCode)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_EVENT));
		eventManagerRepository.deleteByEventCode(eventCode);
	}

}
