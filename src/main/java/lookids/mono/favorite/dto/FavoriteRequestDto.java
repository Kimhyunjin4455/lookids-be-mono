package lookids.mono.favorite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.favorite.domain.Favorite;
import lookids.mono.favorite.domain.FavoriteType;
import lookids.mono.favorite.vo.FavoriteRequestVo;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FavoriteRequestDto {
	private Long id;
	private String uuid;
	private String authorUuid;
	private String targetCode;
	private Boolean favoriteState;
	private FavoriteType favoriteType;

	public static FavoriteRequestDto toDto(FavoriteRequestVo favoriteRequestVo, String uuid) {
		return FavoriteRequestDto.builder()
			.uuid(uuid)
			.authorUuid(favoriteRequestVo.getAuthorUuid())
			.targetCode(favoriteRequestVo.getTargetCode())
			.favoriteType(favoriteRequestVo.getFavoriteType())
			.build();
	}

	public Favorite toEntity() {
		return Favorite.builder()
			.uuid(uuid)
			.targetCode(targetCode)
			.favoriteState(true)
			.favoriteType(favoriteType)
			.build();
	}

	public static Favorite toUpdateEntity(Favorite favorite) {
		return Favorite.builder()
			.id(favorite.getId())
			.uuid(favorite.getUuid())
			.targetCode(favorite.getTargetCode())
			.favoriteState(!favorite.getFavoriteState())
			.favoriteType(favorite.getFavoriteType())
			.build();
	}
}
