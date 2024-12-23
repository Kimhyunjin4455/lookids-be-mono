package lookids.batch.favorite.adaptor.out.infrastructure.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.batch.favorite.domain.FavoriteType;

@Getter
@Entity
@NoArgsConstructor
public class FavoriteLogEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String uuid;

	@Column(nullable = false)
	private String targetCode;

	@Column(nullable = false)
	private String logType;

	@Column(nullable = false)
	private FavoriteType favoriteType;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private Boolean processed;

	@Builder
	public FavoriteLogEntity(Long id, String uuid, String targetCode, String logType, FavoriteType favoriteType,
		LocalDateTime createdAt, Boolean processed) {
		this.id = id;
		this.uuid = uuid;
		this.targetCode = targetCode;
		this.logType = logType;
		this.favoriteType = favoriteType;
		this.createdAt = createdAt;
		this.processed = processed;
	}
}
