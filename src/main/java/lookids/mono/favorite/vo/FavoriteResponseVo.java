package lookids.mono.favorite.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.mono.favorite.domain.FavoriteType;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class FavoriteResponseVo {
	private String uuid;
	private String targetCode;
	private Boolean favoriteState;
	private FavoriteType favoriteType;
}
