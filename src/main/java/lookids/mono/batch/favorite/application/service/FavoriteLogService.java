package lookids.mono.batch.favorite.application.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.batch.favorite.application.mapper.FavoriteDtoMapper;
import lookids.mono.batch.favorite.application.port.dto.FavoriteUpdateEventDto;
import lookids.mono.batch.favorite.application.port.in.FavoriteLogUseCase;
import lookids.mono.batch.favorite.application.port.out.FavoriteRepositoryPort;
import lookids.mono.batch.favorite.domain.model.FavoriteLog;

@Slf4j
@Service
@RequiredArgsConstructor
public class FavoriteLogService implements FavoriteLogUseCase {

	private final FavoriteDtoMapper favoriteDtoMapper;
	private final FavoriteRepositoryPort favoriteRepositoryPort;

	@Override
	public void favoriteUpdate(FavoriteUpdateEventDto favoriteUpdateEventDto) {
		String logType = "create";
		if (!favoriteUpdateEventDto.getFavoriteState()) {
			logType = "delete";
		}
		FavoriteLog favoriteLog = FavoriteLog.builder()
			.uuid(favoriteUpdateEventDto.getUuid())
			.targetCode(favoriteUpdateEventDto.getTargetCode())
			.favoriteType(favoriteUpdateEventDto.getFavoriteType())
			.logType(logType)
			.createdAt(LocalDateTime.now())
			.processed(false)
			.build();
		favoriteRepositoryPort.createLog(favoriteDtoMapper.toFavoriteLogSaveDto(favoriteLog));
	}

}