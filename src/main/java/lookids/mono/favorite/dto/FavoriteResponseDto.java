package lookids.mono.favorite.dto;

import lombok.Builder;
import lombok.Getter;
import lookids.mono.favorite.domain.Favorite;
import lookids.mono.favorite.domain.FavoriteType;
import lookids.mono.favorite.vo.FavoriteResponseVo;

@Getter
@Builder
public class FavoriteResponseDto {
	private String uuid;
	private String targetCode;
	private Boolean favoriteState;
	private FavoriteType favoriteType;

	public static FavoriteResponseDto toDto(Favorite favorite) {
		return FavoriteResponseDto.builder()
			.uuid(favorite.getUuid())
			.targetCode(favorite.getTargetCode())
			.favoriteState(favorite.getFavoriteState())
			.favoriteType(favorite.getFavoriteType())
			.build();
	}

	public FavoriteResponseVo toVo() {
		return FavoriteResponseVo.builder()
			.uuid(uuid)
			.targetCode(targetCode)
			.favoriteState(favoriteState)
			.favoriteType(favoriteType)
			.build();
	}
}
