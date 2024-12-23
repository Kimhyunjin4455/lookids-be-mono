package lookids.batch.favorite.application.port.in;

import lookids.batch.favorite.application.port.dto.FavoriteCountResponseDto;
import lookids.batch.favorite.domain.FavoriteType;

public interface FavoriteCountReadUseCase {

	FavoriteCountResponseDto readFavoriteCount(String targetCode, FavoriteType type);
}
