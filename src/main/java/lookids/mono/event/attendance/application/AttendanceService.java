package lookids.mono.event.attendance.application;

import lookids.mono.event.attendance.dto.AttendanceResponseDto;

public interface AttendanceService {
	void createAttendance(String uuid, String eventCode);

	void updateAttendance(String uuid, String eventCode);

	AttendanceResponseDto readAttendance(String uuid, String eventCode);
}
