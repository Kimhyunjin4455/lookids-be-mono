package lookids.mono.batch.feed.adaptor.in.kafka.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.batch.feed.adaptor.in.kafka.event.FeedDeleteEvent;
import lookids.mono.batch.feed.adaptor.in.kafka.event.FeedEvent;
import lookids.mono.batch.feed.adaptor.in.kafka.mapper.FeedKafkaVoMapper;
import lookids.mono.batch.feed.application.mapper.FeedDtoMapper;
import lookids.mono.batch.feed.application.port.in.FeedLogUseCase;

@Slf4j
@RequiredArgsConstructor
@Component
public class FeedKafkaController {

	private final FeedLogUseCase feedLogUseCase;
	private final FeedDtoMapper feedDtoMapper;
	private final FeedKafkaVoMapper feedKafkaVoMapper;
	private final KafkaTemplate<String, String> favoritekafkaTemplate;

	@KafkaListener(topics = "${feed.create}", groupId = "${group-id.batch}", containerFactory = "feedEventListenerContainerFactory")
	public void consumeFeedEvents(List<FeedEvent> feedEventList, Acknowledgment acknowledgment) {
		try {
			log.info("feed create log processing start");

			feedLogUseCase.feedCreateLog(
				feedEventList.stream().map(feedDtoMapper::toFeedCreateEventDto).collect(Collectors.toList())); // 배치 처리
			log.info("feed create log processing start end");
			// Acknowledgment가 있으면 오프셋 커밋
			if (acknowledgment != null) {
				acknowledgment.acknowledge();
			}
		} catch (Exception e) {
			log.error("Message processing failed: {} ", e);
			// 실패 시 acknowledgment를 호출하지 않음 -> 재시도 가능
		}
	}

	@KafkaListener(topics = "${feed.delete}", groupId = "${group-id.batch}", containerFactory = "feedDeleteEventListenerContainerFactory")
	public void consumeFeedDeleteEvents(List<FeedDeleteEvent> feedDeleteEventList, Acknowledgment acknowledgment) {
		try {
			log.info("feed delete log start");

			feedLogUseCase.feedDeleteLog(feedDeleteEventList.stream()
				.map(feedDtoMapper::toFeedDeleteEventDto)
				.collect(Collectors.toList())); // 배치 처리
			log.info("feed delete log end");
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