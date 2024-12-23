package lookids.batch.favorite.application.port.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.batch.favorite.domain.FavoriteType;

@Getter
@NoArgsConstructor
public class FavoriteUpdateEventDto {
	private String uuid;
	private String targetCode;
	private Boolean favoriteState;
	private FavoriteType favoriteType;

	@Builder
	public FavoriteUpdateEventDto(String uuid, String targetCode, Boolean favoriteState, FavoriteType favoriteType) {
		this.uuid = uuid;
		this.targetCode = targetCode;
		this.favoriteState = favoriteState;
		this.favoriteType = favoriteType;
	}
}
