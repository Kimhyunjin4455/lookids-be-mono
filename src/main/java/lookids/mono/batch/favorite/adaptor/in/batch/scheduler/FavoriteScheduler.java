package lookids.batch.favorite.adaptor.in.batch.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.batch.favorite.application.port.in.FavoriteBatchUseCase;

@Slf4j
@Component
@RequiredArgsConstructor
public class FavoriteScheduler {

	private final FavoriteBatchUseCase favoriteBatchUseCase;

	@Scheduled(cron = "${schedule.favorite-batch-cron}") // 1분마다 실행
	public void favoriteBatchProcessing() {
		log.info("favorite batch Start");
		favoriteBatchUseCase.batchFavoriteCount();
		log.info("favorite batch end");
	}
}
