package lookids.mono.event.attendance.dto;

import java.time.LocalDateTime;

import lookids.mono.event.attendance.domain.Attendance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AttendanceRequestDto {
	private Long id;
	private String uuid;
	private String eventCode;
	private LocalDateTime attendDate;
	private Integer continuousAttend;

	public static Attendance toEntity(String uuid, String eventCode) {
		return Attendance.builder()
			.uuid(uuid)
			.eventCode(eventCode)
			.attendDate(LocalDateTime.now())
			.continuousAttend(1)
			.build();
	}

	public static Attendance toUpdateEntity(Attendance attendance, Integer continuousAttend) {
		return Attendance.builder()
			.id(attendance.getId())
			.uuid(attendance.getUuid())
			.eventCode(attendance.getEventCode())
			.attendDate(LocalDateTime.now())
			.continuousAttend(continuousAttend)
			.build();
	}
}
