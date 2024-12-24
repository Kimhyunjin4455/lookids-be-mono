package lookids.mono.batch.follow.adaptor.in.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.batch.follow.application.port.in.FollowBatchUseCase;

@Slf4j
@Component
@RequiredArgsConstructor
public class FollowScheduler {

	private final FollowBatchUseCase followBatchUseCase;

	//@Scheduled(cron = "30 * * * * ?") // 매 분의 30초에 실행
	@Scheduled(cron = "${schedule.follow-batch-cron}")
	public void followBatchProcessing() {
		log.info("follow batch Start");
		followBatchUseCase.batchFollowCount();
		log.info("follow batch end");
	}
}
