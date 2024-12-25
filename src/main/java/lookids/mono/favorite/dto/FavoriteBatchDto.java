package lookids.mono.favorite.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lookids.mono.favorite.domain.Favorite;
import lookids.mono.favorite.domain.FavoriteType;

@Getter
public class FavoriteBatchDto {

	private String uuid;
	private String targetCode;
	private Boolean favoriteState;

	@JsonProperty("favoriteType")
	private FavoriteType favoriteType;

	@Builder
	public FavoriteBatchDto(String uuid, String targetCode, Boolean favoriteState, FavoriteType favoriteType) {
		this.uuid = uuid;
		this.targetCode = targetCode;
		this.favoriteState = favoriteState;
		this.favoriteType = favoriteType;
	}

	public static FavoriteBatchDto toDto(Favorite favorite) {
		return FavoriteBatchDto.builder()
			.uuid(favorite.getUuid())
			.favoriteType(favorite.getFavoriteType())
			.favoriteState(favorite.getFavoriteState())
			.targetCode(favorite.getTargetCode())
			.build();
	}
}