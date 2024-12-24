package lookids.mono.batch.favorite.adaptor.out.infrastructure.mapper;

import org.springframework.stereotype.Component;

import lookids.mono.batch.favorite.adaptor.out.infrastructure.entity.FavoriteCountEntity;
import lookids.mono.batch.favorite.adaptor.out.infrastructure.entity.FavoriteLogEntity;
import lookids.mono.batch.favorite.application.port.dto.FavoriteCountSaveDto;
import lookids.mono.batch.favorite.application.port.dto.FavoriteLogSaveDto;
import lookids.mono.batch.favorite.domain.FavoriteType;
import lookids.mono.batch.favorite.domain.model.FavoriteCount;
import lookids.mono.batch.favorite.domain.model.FavoriteLog;

@Component
public class FavoriteEntityMapper {

	public FavoriteLogEntity toFavoriteLogEntity(FavoriteLogSaveDto favoriteLogSaveDto) {
		return FavoriteLogEntity.builder()
			.id(favoriteLogSaveDto.getId())
			.uuid(favoriteLogSaveDto.getUuid())
			.targetCode(favoriteLogSaveDto.getTargetCode())
			.favoriteType(favoriteLogSaveDto.getFavoriteType())
			.logType(favoriteLogSaveDto.getLogType())
			.createdAt(favoriteLogSaveDto.getCreatedAt())
			.processed(favoriteLogSaveDto.getProcessed())
			.build();
	}

	public FavoriteCount toFavoriteCount(FavoriteCountEntity favoriteCountEntity) {
		return FavoriteCount.builder()
			.id(favoriteCountEntity.getId())
			.targetCode(favoriteCountEntity.getTargetCode())
			.type(favoriteCountEntity.getType())
			.count(favoriteCountEntity.getCount())
			.updateAt(favoriteCountEntity.getUpdateAt())
			.build();
	}

	public FavoriteCountEntity toFavoriteCountEntity(FavoriteCountSaveDto favoriteCountSaveDto) {
		return FavoriteCountEntity.builder()
			.id(favoriteCountSaveDto.getId())
			.targetCode(favoriteCountSaveDto.getTargetCode())
			.type(favoriteCountSaveDto.getType())
			.count(favoriteCountSaveDto.getCount())
			.updateAt(favoriteCountSaveDto.getUpdateAt())
			.build();
	}

	public FavoriteCount toNullFavoriteCount(String targetCode, FavoriteType type) {
		return FavoriteCount.builder().targetCode(targetCode).type(type).count(0).build();
	}

	public FavoriteLog toFavoriteLog(FavoriteLogEntity favoriteLogEntity) {
		return FavoriteLog.builder()
			.id(favoriteLogEntity.getId())
			.uuid(favoriteLogEntity.getUuid())
			.targetCode(favoriteLogEntity.getTargetCode())
			.favoriteType(favoriteLogEntity.getFavoriteType())
			.logType(favoriteLogEntity.getLogType())
			.createdAt(favoriteLogEntity.getCreatedAt())
			.processed(favoriteLogEntity.getProcessed())
			.build();
	}

}
