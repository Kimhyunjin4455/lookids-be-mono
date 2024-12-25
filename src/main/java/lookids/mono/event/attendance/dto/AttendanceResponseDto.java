package lookids.mono.event.attendance.dto;

import lookids.mono.event.attendance.domain.Attendance;
import lookids.mono.event.attendance.vo.AttendanceResponseVo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AttendanceResponseDto {
	private String uuid;
	private String eventCode;
	private Integer continuousAttend;

	public static AttendanceResponseDto toDto(Attendance attendance) {
		return AttendanceResponseDto.builder()
			.uuid(attendance.getUuid())
			.eventCode(attendance.getEventCode())
			.continuousAttend(attendance.getContinuousAttend())
			.build();
	}

	public AttendanceResponseVo toVo() {
		return AttendanceResponseVo.builder()
			.uuid(uuid)
			.eventCode(eventCode)
			.continuousAttend(continuousAttend)
			.build();
	}
}
