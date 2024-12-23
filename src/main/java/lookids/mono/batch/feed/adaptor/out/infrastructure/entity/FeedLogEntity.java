package lookids.batch.feed.adaptor.out.infrastructure.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class FeedLogEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String feedCode;

	@Column(nullable = false)
	private String uuid;

	// @ElementCollection
	// @Column(columnDefinition = "json")
	// private List<String> petCode;

	@Column(nullable = false)
	private String logType;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private Boolean processed;

	@Builder
	public FeedLogEntity(Long id, String feedCode, String uuid, String logType, List<String> petCode,
		LocalDateTime createdAt, Boolean processed) {
		this.id = id;
		this.feedCode = feedCode;
		this.uuid = uuid;
		this.logType = logType;
		//this.petCode = petCode;
		this.createdAt = createdAt;
		this.processed = processed;
	}

}
