package lookids.batch.favorite.application.port.out;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lookids.batch.favorite.application.port.dto.FavoriteBatchDto;
import lookids.batch.favorite.application.port.dto.FavoriteCountSaveDto;
import lookids.batch.favorite.application.port.dto.FavoriteLogSaveDto;
import lookids.batch.favorite.domain.FavoriteType;
import lookids.batch.favorite.domain.model.FavoriteCount;
import lookids.batch.favorite.domain.model.FavoriteLog;

public interface FavoriteRepositoryPort {
	void createLog(FavoriteLogSaveDto favoriteLogSaveDto);

	FavoriteCount readFavoriteCount(String targetCode, FavoriteType type);

	Page<FavoriteLog> findUnprocessedLogs(Pageable pageable);

	void markLogsAsProcessed(List<FavoriteLogSaveDto> favoriteLogSaveDtoList);

	List<FavoriteCount> findFavoriteCountsByTargetKeys(List<FavoriteBatchDto> favoriteBatchDto);

	void updateFavoriteCounts(List<FavoriteCountSaveDto> favoriteCountSaveDtoList);
}
