package lookids.manager.event.dto.in;

import java.time.LocalDateTime;

import org.w3c.dom.Text;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.manager.event.domain.Event;
import lookids.manager.event.vo.in.EventRequestVo;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventRequestDto {

	private String thumbnail;
	private String eventName;
	private String content;
	private String description;
	private LocalDateTime expiredAt;

	public Event toEntity(String eventCode) {
		return Event.builder()
			.eventCode(eventCode)
			.thumbnail(thumbnail)
			.eventName(eventName)
			.content(content)
			.description(description)
			.expiredAt(expiredAt)
			.build();
	}

	public static EventRequestDto toDto(EventRequestVo eventRequestVo) {
		return EventRequestDto.builder()
			.thumbnail(eventRequestVo.getThumbnail())
			.eventName(eventRequestVo.getEventName())
			.content(eventRequestVo.getContent())
			.description(eventRequestVo.getDescription())
			.expiredAt(eventRequestVo.getExpiredAt())
			.build();
	}
}
