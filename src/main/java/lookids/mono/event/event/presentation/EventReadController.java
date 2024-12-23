package com.lookids.event.event.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lookids.event.common.entity.BaseResponse;
import com.lookids.event.event.application.EventService;
import com.lookids.event.event.dto.EventResponseDto;
import com.lookids.event.event.vo.EventResponseVo;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/read/event")
@RestController
public class EventReadController {
	private final EventService eventService;

	@Operation(summary = "Read Event API", description = "Read Event API 입니다", tags = {"Event"})
	@GetMapping("/{eventCode}")
	public BaseResponse<EventResponseVo> readEvent(
		@RequestParam String eventCode
	) {
		return new BaseResponse<>(
			eventService.readEvent(eventCode).toVo()
		);
	}

	@Operation(summary = "Read Event API", description = "Read Event API 입니다", tags = {"Event"})
	@GetMapping("/activeEventList")
	public BaseResponse<List<EventResponseVo>> readActiveEventList(
	) {

		return new BaseResponse<>(
			eventService.readActiveEventList().stream().map(EventResponseDto::toVo).toList()
		);
	}

	@Operation(summary = "Read Event API", description = "Read Event API 입니다", tags = {"Event"})
	@GetMapping("deactivateEventList")
	public BaseResponse<List<EventResponseVo>> readDeactivateEventList(
	) {

		return new BaseResponse<>(
			eventService.readDeactivateEventList().stream().map(EventResponseDto::toVo).toList()
		);
	}
}
