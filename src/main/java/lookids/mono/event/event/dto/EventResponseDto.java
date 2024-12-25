package lookids.mono.event.event.dto;

import java.time.LocalDateTime;

import lookids.mono.event.event.domain.Event;
import lookids.mono.event.event.vo.EventResponseVo;

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
public class EventResponseDto {
	private String eventCode;
	private String thumbnail;
	private String name;
	private String content;
	private String description;
	private LocalDateTime startedAt;
	private LocalDateTime expiredAt;

	public static EventResponseDto toDto(Event event) {
		return EventResponseDto.builder()
			.eventCode(event.getEventCode())
			.thumbnail(event.getThumbnail())
			.name(event.getName())
			.content(event.getContent())
			.description(event.getDescription())
			.startedAt(event.getStartedAt())
			.expiredAt(event.getExpiredAt())
			.build();
	}

	public EventResponseVo toVo() {
		return EventResponseVo.builder()
			.eventCode(eventCode)
			.thumbnail(thumbnail)
			.name(name)
			.content(content)
			.description(description)
			.startedAt(startedAt)
			.expiredAt(expiredAt)
			.build();
	}
}
