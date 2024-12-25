package lookids.mono.event.event.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.mono.event.event.domain.Event;
import lookids.mono.event.event.vo.EventRequestVo;
import lookids.mono.event.event.vo.EventUpdateRequestVo;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventRequestDto {
	private Long id;
	private String eventCode;
	private String thumbnail;
	private String name;
	private String content;
	private String description;
	private Boolean state;
	private LocalDateTime startedAt;
	private LocalDateTime expiredAt;

	public static EventRequestDto toDto(EventRequestVo eventRequestVo) {
		return EventRequestDto.builder()
			.thumbnail(eventRequestVo.getThumbnail())
			.name(eventRequestVo.getName())
			.content(eventRequestVo.getContent())
			.description(eventRequestVo.getDescription())
			.startedAt(eventRequestVo.getStartedAt())
			.expiredAt(eventRequestVo.getExpiredAt())
			.build();
	}

	public static EventRequestDto toDto(EventUpdateRequestVo eventUpdateRequestVo) {
		return EventRequestDto.builder()
			.eventCode(eventUpdateRequestVo.getEventCode())
			.thumbnail(eventUpdateRequestVo.getThumbnail())
			.name(eventUpdateRequestVo.getName())
			.content(eventUpdateRequestVo.getContent())
			.description(eventUpdateRequestVo.getDescription())
			.startedAt(eventUpdateRequestVo.getStartedAt())
			.expiredAt(eventUpdateRequestVo.getExpiredAt())
			.build();
	}

	public Event toEntity() {
		return Event.builder()
			.eventCode(UUID.randomUUID().toString())
			.thumbnail(thumbnail)
			.name(name)
			.content(content)
			.description(description)
			.startedAt(startedAt)
			.expiredAt(expiredAt)
			.state(true)
			.build();
	}

	public Event updateEntity(Event event) {
		return Event.builder()
			.id(event.getId())
			.eventCode(event.getEventCode())
			.thumbnail(thumbnail)
			.name(name)
			.content(content)
			.description(description)
			.startedAt(startedAt)
			.expiredAt(expiredAt)
			.state(event.getState())
			.build();
	}

	public static Event deleteEntity(Event event) {
		return Event.builder()
			.id(event.getId())
			.eventCode(event.getEventCode())
			.thumbnail(event.getThumbnail())
			.name(event.getName())
			.content(event.getContent())
			.description(event.getDescription())
			.startedAt(event.getStartedAt())
			.expiredAt(event.getExpiredAt())
			.state(false)
			.build();
	}

}
