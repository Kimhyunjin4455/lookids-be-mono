package com.lookids.event.attendance.application;

import com.lookids.event.attendance.dto.AttendanceResponseDto;

public interface AttendanceService {
	void createAttendance(String uuid, String eventCode);

	void updateAttendance(String uuid, String eventCode);

	AttendanceResponseDto readAttendance(String uuid, String eventCode);
}
