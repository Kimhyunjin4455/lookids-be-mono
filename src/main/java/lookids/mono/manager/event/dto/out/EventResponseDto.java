package lookids.manager.event.dto.out;

import java.time.LocalDateTime;

import org.w3c.dom.Text;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.manager.event.domain.Event;
import lookids.manager.event.vo.out.EventResponseVo;

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

	public static EventResponseDto toDto(Event event) {
		return EventResponseDto.builder()
			.eventCode(event.getEventCode())
			.thumbnail(event.getThumbnail())
			.eventName(event.getEventName())
			.content(event.getContent())
			.description(event.getDescription())
			.expiredAt(event.getExpiredAt())
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
