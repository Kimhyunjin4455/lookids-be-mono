package com.lookids.event.attendance.presentation;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lookids.event.attendance.application.AttendanceService;
import com.lookids.event.common.entity.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/write/attendance")
public class AttendanceWriteController {
	private final AttendanceService attendanceService;

	@Operation(summary = "Put Attendance API", description = "출석체크 시 요청되는 API", tags = {"Attendance"})
	@PutMapping("/{uuid}/{eventCode}")
	public BaseResponse<Void> updateAttendance(
		@RequestParam String uuid,
		@RequestParam String eventCode
	) {
		attendanceService.updateAttendance(uuid, eventCode);
		return new BaseResponse<>();
	}
}
