package lookids.mono.favorite.vo;

import lombok.Getter;
import lookids.mono.favorite.domain.FavoriteType;

@Getter
public class FavoriteRequestVo {
	private String authorUuid;
	private String targetCode;
	private FavoriteType favoriteType;
}
