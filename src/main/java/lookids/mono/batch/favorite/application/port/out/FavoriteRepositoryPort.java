package lookids.mono.batch.favorite.application.port.out;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lookids.mono.batch.favorite.application.port.dto.FavoriteBatchDto;
import lookids.mono.batch.favorite.application.port.dto.FavoriteCountSaveDto;
import lookids.mono.batch.favorite.application.port.dto.FavoriteLogSaveDto;
import lookids.mono.batch.favorite.domain.FavoriteType;
import lookids.mono.batch.favorite.domain.model.FavoriteCount;
import lookids.mono.batch.favorite.domain.model.FavoriteLog;

public interface FavoriteRepositoryPort {
	void createLog(FavoriteLogSaveDto favoriteLogSaveDto);

	FavoriteCount readFavoriteCount(String targetCode, FavoriteType type);

	Page<FavoriteLog> findUnprocessedLogs(Pageable pageable);

	void markLogsAsProcessed(List<FavoriteLogSaveDto> favoriteLogSaveDtoList);

	List<FavoriteCount> findFavoriteCountsByTargetKeys(List<FavoriteBatchDto> favoriteBatchDto);

	void updateFavoriteCounts(List<FavoriteCountSaveDto> favoriteCountSaveDtoList);
}
