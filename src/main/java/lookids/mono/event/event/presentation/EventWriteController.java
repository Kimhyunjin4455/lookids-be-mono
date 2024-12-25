package lookids.mono.event.event.presentation;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lookids.mono.common.entity.BaseResponse;
import lookids.mono.event.event.application.EventService;
import lookids.mono.event.event.dto.EventRequestDto;
import lookids.mono.event.event.vo.EventRequestVo;
import lookids.mono.event.event.vo.EventUpdateRequestVo;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/event-service/write/event")
@RestController
public class EventWriteController {
	private final EventService eventService;

	@Operation(summary = "Create Event API", description = "Create Event API 입니다", tags = {"Event"})
	@PostMapping()
	public BaseResponse<Void> createEvent(@RequestBody EventRequestVo eventRequestVo) {

		eventService.createEvent(EventRequestDto.toDto(eventRequestVo));
		return new BaseResponse<>();
	}

	@Operation(summary = "Update Event API", description = "Update Event API 입니다", tags = {"Event"})
	@PutMapping()
	public BaseResponse<Void> updateEvent(@RequestBody EventUpdateRequestVo eventUpdateRequestVo) {
		eventService.updateEvent(EventRequestDto.toDto(eventUpdateRequestVo));
		return new BaseResponse<>();
	}

	@Operation(summary = "Delete Event API", description = "Delete Event API 입니다", tags = {"Event"})
	@DeleteMapping()
	public BaseResponse<Void> deleteEvent(@RequestParam String eventCode) {
		eventService.deleteEvent(eventCode);
		return new BaseResponse<>();
	}

}
