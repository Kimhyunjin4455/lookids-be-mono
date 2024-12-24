package lookids.mono.batch.favorite.adaptor.in.kafka.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.mono.batch.favorite.domain.FavoriteType;

@ToString
@NoArgsConstructor
@Getter
public class FavoriteEvent {
	private String uuid;
	private String targetCode;
	private Boolean favoriteState;
	private FavoriteType favoriteType;

}
