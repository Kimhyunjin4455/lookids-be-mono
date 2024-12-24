package lookids.mono.batch.favorite.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.mono.batch.favorite.domain.FavoriteType;

@ToString
@NoArgsConstructor
@Getter
public class FavoriteCount {
	private Long id;
	private String targetCode;
	private Integer count;
	private FavoriteType type;
	private LocalDateTime updateAt;

	@Builder
	public FavoriteCount(Long id, String targetCode, Integer count, FavoriteType type, LocalDateTime updateAt) {
		this.id = id;
		this.targetCode = targetCode;
		this.count = count;
		this.type = type;
		this.updateAt = updateAt;
	}
}
