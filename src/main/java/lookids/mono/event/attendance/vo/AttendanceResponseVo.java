package com.lookids.event.attendance.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceResponseVo {
	private String uuid;
	private String eventCode;
	private Integer continuousAttend;
}
