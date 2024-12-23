package lookids.manager.event.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.manager.common.entity.BaseEntity;
import lookids.manager.event.dto.in.EventRequestDto;
import lookids.manager.event.dto.in.EventUpdateRequestDto;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String eventCode;

	@Column(nullable = false)
	private String thumbnail;

	@Column(nullable = false)
	private String eventName;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private LocalDateTime expiredAt;

	public void update(EventUpdateRequestDto eventUpdateRequestDto) {
		this.eventName = eventUpdateRequestDto.getEventName();
		this.content = eventUpdateRequestDto.getContent();
		this.description = eventUpdateRequestDto.getDescription();
		this.expiredAt = eventUpdateRequestDto.getExpiredAt();
	}
}
