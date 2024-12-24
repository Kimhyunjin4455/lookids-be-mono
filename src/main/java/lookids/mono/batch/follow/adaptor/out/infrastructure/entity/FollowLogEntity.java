package lookids.mono.batch.follow.adaptor.out.infrastructure.entity;

import java.time.LocalDateTime;

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
public class FollowLogEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String senderUuid;

	@Column(nullable = false)
	private String receiverUuid;

	@Column(nullable = false)
	private String logType;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private Boolean processed;

	@Builder
	public FollowLogEntity(Long id, String senderUuid, String receiverUuid, String logType, LocalDateTime createdAt,
		Boolean processed) {
		this.id = id;
		this.senderUuid = senderUuid;
		this.receiverUuid = receiverUuid;
		this.logType = logType;
		this.createdAt = createdAt;
		this.processed = processed;
	}
}
