package com.lookids.event.event.vo;

import java.time.LocalDateTime;

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
public class EventResponseVo {
	private String eventCode;
	private String thumbnail;
	private String name;
	private String content;
	private String description;
	private LocalDateTime startedAt;
	private LocalDateTime expiredAt;
}
