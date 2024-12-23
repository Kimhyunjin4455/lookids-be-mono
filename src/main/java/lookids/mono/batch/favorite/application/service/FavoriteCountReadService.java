package lookids.batch.favorite.application.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.batch.favorite.application.mapper.FavoriteDtoMapper;
import lookids.batch.favorite.application.port.dto.FavoriteCountResponseDto;
import lookids.batch.favorite.application.port.in.FavoriteCountReadUseCase;
import lookids.batch.favorite.application.port.out.FavoriteRepositoryPort;
import lookids.batch.favorite.domain.FavoriteType;

@Slf4j
@Service
@RequiredArgsConstructor
public class FavoriteCountReadService implements FavoriteCountReadUseCase {
	private final FavoriteDtoMapper favoriteDtoMapper;
	private final FavoriteRepositoryPort favoriteRepositoryPort;

	@Override
	public FavoriteCountResponseDto readFavoriteCount(String targetCode, FavoriteType type) {
		return favoriteDtoMapper.toFavoriteCountResponseDto(favoriteRepositoryPort.readFavoriteCount(targetCode, type));
	}
}
