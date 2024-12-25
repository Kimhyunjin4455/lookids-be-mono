package lookids.mono.manager.event.dto.out;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.manager.event.domain.EventManager;
import lookids.mono.manager.event.vo.out.EventResponseVo;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDto {

	private String eventCode;
	private String thumbnail;
	private String eventName;
	private String content;
	private String description;
	private LocalDateTime expiredAt;

	public static EventResponseDto toDto(EventManager eventManager) {
		return EventResponseDto.builder()
			.eventCode(eventManager.getEventCode())
			.thumbnail(eventManager.getThumbnail())
			.eventName(eventManager.getEventName())
			.content(eventManager.getContent())
			.description(eventManager.getDescription())
			.expiredAt(eventManager.getExpiredAt())
			.build();
	}

	public EventResponseVo toVo() {
		return EventResponseVo.builder()
			.eventCode(eventCode)
			.thumbnail(thumbnail)
			.eventName(eventName)
			.content(content)
			.description(description)
			.expiredAt(expiredAt)
			.build();
	}
}
