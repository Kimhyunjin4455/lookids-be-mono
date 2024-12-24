package lookids.mono.batch.favorite.application.port.in;

import lookids.mono.batch.favorite.application.port.dto.FavoriteUpdateEventDto;

public interface FavoriteLogUseCase {

	void favoriteUpdate(FavoriteUpdateEventDto favoriteEven);
}
