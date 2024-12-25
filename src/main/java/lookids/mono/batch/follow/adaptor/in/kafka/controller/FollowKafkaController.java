package lookids.mono.batch.follow.adaptor.in.kafka.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.batch.follow.adaptor.in.kafka.event.FollowEvent;
import lookids.mono.batch.follow.adaptor.in.kafka.mapper.FollowKafkaVoMapper;
import lookids.mono.batch.follow.application.mapper.FollowDtoMapper;
import lookids.mono.batch.follow.application.port.in.FollowLogUseCase;

@Slf4j
@RequiredArgsConstructor
@Component
public class FollowKafkaController {

	private final FollowLogUseCase followLogUseCase;
	private final FollowDtoMapper followDtoMapper;
	private final FollowKafkaVoMapper followKafkaVoMapper;
	private final KafkaTemplate<String, String> favoritekafkaTemplate;

	@KafkaListener(topics = "${follow.create}", groupId = "${group-id.batch}", containerFactory = "followEventListenerContainerFactory")
	public void consumeFollowEvents(List<FollowEvent> followEventList, Acknowledgment acknowledgment) {
		try {
			log.info("follow create log processing start");
			followLogUseCase.followCreateLog(
				followEventList.stream().map(followDtoMapper::toFollowEventDto).collect(Collectors.toList())); // 배치 처리
			log.info("follow create log processing end");
			// Acknowledgment가 있으면 오프셋 커밋
			if (acknowledgment != null) {
				acknowledgment.acknowledge();
			}
		} catch (Exception e) {
			log.error("Message processing failed: {} ", e);
			// 실패 시 acknowledgment를 호출하지 않음 -> 재시도 가능
		}
	}

	@KafkaListener(topics = "${follow.delete}", groupId = "${group-id.batch}", containerFactory = "followEventListenerContainerFactory")
	public void consumeFollowDeleteEvents(List<FollowEvent> followEventList, Acknowledgment acknowledgment) {
		try {
			log.info("follow delete log processing start");
			followLogUseCase.followDeleteLog(
				followEventList.stream().map(followDtoMapper::toFollowEventDto).collect(Collectors.toList())); // 배치 처리
			log.info("follow delete log processing end");
			// Acknowledgment가 있으면 오프셋 커밋
			if (acknowledgment != null) {
				acknowledgment.acknowledge();
			}
		} catch (Exception e) {
			log.error("Message processing failed: {} ", e);
			// 실패 시 acknowledgment를 호출하지 않음 -> 재시도 가능
		}
	}
}