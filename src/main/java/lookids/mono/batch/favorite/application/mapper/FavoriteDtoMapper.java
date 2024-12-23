package lookids.batch.favorite.application.mapper;

import org.springframework.stereotype.Component;

import lookids.batch.favorite.adaptor.in.kafka.event.FavoriteEvent;
import lookids.batch.favorite.application.port.dto.FavoriteBatchDto;
import lookids.batch.favorite.application.port.dto.FavoriteCountResponseDto;
import lookids.batch.favorite.application.port.dto.FavoriteCountSaveDto;
import lookids.batch.favorite.application.port.dto.FavoriteLogSaveDto;
import lookids.batch.favorite.application.port.dto.FavoriteUpdateEventDto;
import lookids.batch.favorite.domain.model.FavoriteCount;
import lookids.batch.favorite.domain.model.FavoriteLog;
import lookids.batch.favorite.domain.model.TargetKey;

@Component
public class FavoriteDtoMapper {

	public FavoriteUpdateEventDto toFavoriteUpdateEventDto(FavoriteEvent favoriteEvent) {
		return FavoriteUpdateEventDto.builder()
			.uuid(favoriteEvent.getUuid())
			.targetCode(favoriteEvent.getTargetCode())
			.favoriteState(favoriteEvent.getFavoriteState())
			.favoriteType(favoriteEvent.getFavoriteType())
			.build();
	}

	public FavoriteLogSaveDto toFavoriteLogSaveDto(FavoriteLog favoriteLog) {
		return FavoriteLogSaveDto.builder()
			.uuid(favoriteLog.getUuid())
			.targetCode(favoriteLog.getTargetCode())
			.favoriteType(favoriteLog.getFavoriteType())
			.logType(favoriteLog.getLogType())
			.createdAt(favoriteLog.getCreatedAt())
			.processed(favoriteLog.getProcessed())
			.build();
	}

	public FavoriteLogSaveDto toFavoriteLogUpdateDto(FavoriteLog favoriteLog) {
		return FavoriteLogSaveDto.builder()
			.id(favoriteLog.getId())
			.uuid(favoriteLog.getUuid())
			.targetCode(favoriteLog.getTargetCode())
			.favoriteType(favoriteLog.getFavoriteType())
			.logType(favoriteLog.getLogType())
			.createdAt(favoriteLog.getCreatedAt())
			.processed(true)
			.build();
	}

	public FavoriteCountResponseDto toFavoriteCountResponseDto(FavoriteCount favoriteCount) {
		return FavoriteCountResponseDto.builder().count(favoriteCount.getCount()).build();
	}

	public FavoriteBatchDto toFavoriteBatchDto(TargetKey targetKey, Integer count) {
		return FavoriteBatchDto.builder()
			.targetCode(targetKey.getTargetCode())
			.favoriteType(targetKey.getFavoriteType())
			.count(count)
			.build();
	}

	public FavoriteCountSaveDto toFavoriteCountSaveDto(FavoriteCount favoriteCount) {
		return FavoriteCountSaveDto.builder()
			.id(favoriteCount.getId())
			.targetCode(favoriteCount.getTargetCode())
			.type(favoriteCount.getType())
			.count(favoriteCount.getCount())
			.updateAt(favoriteCount.getUpdateAt())
			.build();
	}
}
