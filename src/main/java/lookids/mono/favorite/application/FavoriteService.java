package lookids.mono.favorite.application;

import java.util.List;

import org.springframework.data.domain.Page;

import lookids.mono.favorite.domain.FavoriteType;
import lookids.mono.favorite.dto.FavoriteRequestDto;
import lookids.mono.favorite.dto.FavoriteResponseDto;

public interface FavoriteService {
	void createFavorite(FavoriteRequestDto favoriteRequestDto);

	void updateFavorite(FavoriteRequestDto favoriteRequestDto);

	List<FavoriteResponseDto> readUserFavoriteList(String uuid);

	Page<FavoriteResponseDto> readFeedFavoriteList(String targetCode, FavoriteType favoriteType, int page, int size);

	Boolean readFavorite(String uuid, String targetCode);
}
