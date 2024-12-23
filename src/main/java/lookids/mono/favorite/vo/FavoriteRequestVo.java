package lookids.favorite.favorite.vo;

import lombok.Getter;
import lookids.favorite.favorite.domain.FavoriteType;

@Getter
public class FavoriteRequestVo {
	private String authorUuid;
	private String targetCode;
	private FavoriteType favoriteType;
}
