package lookids.mono.event.event.vo;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class EventUpdateRequestVo {
	private String eventCode;
	private String thumbnail;
	private String name;
	private String content;
	private String description;
	private LocalDateTime startedAt;
	private LocalDateTime expiredAt;
}
