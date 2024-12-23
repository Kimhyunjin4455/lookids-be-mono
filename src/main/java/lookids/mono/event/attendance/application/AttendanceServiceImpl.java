package com.lookids.event.attendance.application;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.lookids.event.attendance.domain.Attendance;
import com.lookids.event.attendance.dto.AttendanceRequestDto;
import com.lookids.event.attendance.dto.AttendanceResponseDto;
import com.lookids.event.attendance.infrastructure.AttendanceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {
	private final AttendanceRepository attendanceRepository;

	@Override
	public void createAttendance(String uuid, String eventCode) {
		attendanceRepository.save(AttendanceRequestDto.toEntity(uuid, eventCode));
	}

	@Override
	public void updateAttendance(String uuid, String eventCode) {
		Attendance attendance = attendanceRepository.findByUuidAndEventCode(uuid, eventCode).orElse(null);
		if (attendance == null) {
			createAttendance(uuid, eventCode);
		} else {
			LocalDate lastAttendDate = attendance.getAttendDate().toLocalDate();
			LocalDate today = LocalDate.now();

			if (ChronoUnit.DAYS.between(lastAttendDate, today) == 1) {
				// 마지막 출석 날짜와 오늘이 연속적이라면 continuousAttend 증가
				attendanceRepository.save(AttendanceRequestDto.toUpdateEntity(attendance,
					attendance.getContinuousAttend() + 1));
			} else {
				// 연속 출석이 아닌 경우 continuousAttend 초기화
				attendanceRepository.save(AttendanceRequestDto.toUpdateEntity(attendance, 1));
			}

		}
	}

	@Override
	public AttendanceResponseDto readAttendance(String uuid, String eventCode) {
		return AttendanceResponseDto.toDto(attendanceRepository.findByUuidAndEventCode(uuid, eventCode).orElse(null));
	}
}
