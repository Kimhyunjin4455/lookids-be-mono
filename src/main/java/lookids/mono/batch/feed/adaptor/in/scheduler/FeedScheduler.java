package lookids.mono.batch.feed.adaptor.in.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.batch.feed.application.port.in.FeedBatchUseCase;

@Slf4j
@Component
@RequiredArgsConstructor
public class FeedScheduler {

	private final FeedBatchUseCase feedBatchUseCase;

	@Scheduled(cron = "${schedule.feed-batch-cron}") // 매 분의 15초에 실행
	public void feedBatchProcessing() {
		log.info("feed batch Start");
		feedBatchUseCase.batchFeedCount();
		log.info("feed batch end");
	}
}
