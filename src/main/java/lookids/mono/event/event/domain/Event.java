package lookids.mono.event.event.domain;

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
import lookids.mono.common.entity.BaseEntity;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
public class Event extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String eventCode;

	@Column(nullable = false)
	private String thumbnail;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private Boolean state;

	@Column(nullable = false)
	private LocalDateTime startedAt;

	@Column(nullable = false)
	private LocalDateTime expiredAt;

	@Builder
	public Event(Long id, String eventCode, String thumbnail, String name, String content, String description,
		Boolean state, LocalDateTime startedAt, LocalDateTime expiredAt) {
		this.id = id;
		this.eventCode = eventCode;
		this.thumbnail = thumbnail;
		this.name = name;
		this.content = content;
		this.description = description;
		this.state = state;
		this.startedAt = startedAt;
		this.expiredAt = expiredAt;
	}

}
