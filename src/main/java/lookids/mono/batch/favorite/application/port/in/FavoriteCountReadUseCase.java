package lookids.mono.batch.favorite.application.port.in;

import lookids.mono.batch.favorite.application.port.dto.FavoriteCountResponseDto;
import lookids.mono.batch.favorite.domain.FavoriteType;

public interface FavoriteCountReadUseCase {

	FavoriteCountResponseDto readFavoriteCount(String targetCode, FavoriteType type);
}
