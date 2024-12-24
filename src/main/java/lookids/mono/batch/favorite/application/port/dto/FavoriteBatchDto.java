package lookids.mono.batch.favorite.application.port.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.batch.favorite.domain.FavoriteType;

@Getter
@NoArgsConstructor
public class FavoriteBatchDto {
	private String targetCode;
	private FavoriteType favoriteType;
	private Integer count;

	@Builder
	public FavoriteBatchDto(String targetCode, FavoriteType favoriteType, Integer count) {

		this.targetCode = targetCode;
		this.favoriteType = favoriteType;
		this.count = count;
	}
}
