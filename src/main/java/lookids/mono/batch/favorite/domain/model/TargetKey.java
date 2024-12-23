package lookids.batch.favorite.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lookids.batch.favorite.domain.FavoriteType;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class TargetKey {
	private String targetCode;
	private FavoriteType favoriteType;
}