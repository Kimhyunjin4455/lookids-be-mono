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
public class FavoriteCountEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String targetCode;

	@Column(nullable = false)
	private FavoriteType type;

	@Column(nullable = false)
	private Integer count;

	@Column(nullable = false)
	private LocalDateTime updateAt;

	@Builder
	public FavoriteCountEntity(Long id, String targetCode, FavoriteType type, Integer count, LocalDateTime updateAt) {
		this.id = id;
		this.targetCode = targetCode;
		this.type = type;
		this.count = count;
		this.updateAt = updateAt;
	}

}
