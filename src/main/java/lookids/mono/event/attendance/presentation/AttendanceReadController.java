package lookids.mono.event.attendance.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lookids.mono.event.attendance.application.AttendanceService;
import lookids.mono.event.attendance.vo.AttendanceResponseVo;
import lookids.mono.common.entity.BaseResponse;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event-service/read/attendance")
public class AttendanceReadController {
	private final AttendanceService attendanceService;

	@Operation(summary = "Read Attendance API", description = "출석체크 시 요청되는 API", tags = {"Attendance"})
	@GetMapping("/{uuid}/{eventCode}")
	public BaseResponse<AttendanceResponseVo> readAttendance(@RequestHeader String uuid,
		@RequestParam String eventCode) {

		return new BaseResponse<>(attendanceService.readAttendance(uuid, eventCode).toVo());
	}
}
