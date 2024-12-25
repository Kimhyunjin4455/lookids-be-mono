package lookids.mono.favorite.domain;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.common.entity.BaseEntity;

@Getter
@NoArgsConstructor
@DynamicUpdate  //특정 칼럼만 update하기 위해 사용
@Entity
public class Favorite extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String uuid;

	@Column(nullable = false)
	private String targetCode;

	@Column(nullable = false)
	@ColumnDefault("false")
	private Boolean favoriteState;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private FavoriteType favoriteType;

	@Builder
	public Favorite(Long id, String uuid, String targetCode, Boolean favoriteState, FavoriteType favoriteType) {
		this.id = id;
		this.uuid = uuid;
		this.targetCode = targetCode;
		this.favoriteState = favoriteState;
		this.favoriteType = favoriteType;
	}

}
