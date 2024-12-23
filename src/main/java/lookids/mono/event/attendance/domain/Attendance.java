package com.lookids.event.attendance.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String uuid;

	@Column(nullable = false)
	private String eventCode;

	@Column(nullable = false)
	private LocalDateTime attendDate;

	@Column(nullable = false)
	private Integer continuousAttend;

	@Builder
	public Attendance(Long id, String uuid, String eventCode, LocalDateTime attendDate, Integer continuousAttend) {
		this.id = id;
		this.uuid = uuid;
		this.eventCode = eventCode;
		this.attendDate = attendDate;
		this.continuousAttend = continuousAttend;
	}
}
