package lookids.mono.batch.comment.adaptor.in.kafka.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.batch.comment.adaptor.in.kafka.event.CommentEvent;
import lookids.mono.batch.comment.adaptor.in.kafka.event.ReplyEvent;
import lookids.mono.batch.comment.application.mapper.CommentDtoMapper;
import lookids.mono.batch.comment.application.port.in.CommentLogUseCase;

@Slf4j
@RequiredArgsConstructor
@Component
public class CommentKafkaController {

	private final CommentLogUseCase commentLogUseCase;
	private final CommentDtoMapper commentDtoMapper;

	@KafkaListener(topics = "${comment.create}", groupId = "${group-id.batch}", containerFactory = "commentBatchListenerContainerFactory")
	public void consumeCommentEvents(List<CommentEvent> commentEventList, Acknowledgment acknowledgment) {
		try {
			log.info("comment create log processing start");
			commentLogUseCase.commentCreateLog(commentEventList.stream()
				.map(commentDtoMapper::toCommentCreateEventDto)
				.collect(Collectors.toList())); // 배치 처리
			log.info("comment create log processing end");
			// Acknowledgment가 있으면 오프셋 커밋
			if (acknowledgment != null) {
				acknowledgment.acknowledge();
			}
		} catch (Exception e) {
			log.error("Message processing failed: {} ", e);
			// 실패 시 acknowledgment를 호출하지 않음 -> 재시도 가능
		}
	}

	@KafkaListener(topics = "${comment.delete}", groupId = "${group-id.batch}", containerFactory = "commentBatchListenerContainerFactory")
	public void consumeCommentDeleteEvents(List<CommentEvent> commentEventList, Acknowledgment acknowledgment) {
		try {
			log.info("comment delete log processing start");
			commentLogUseCase.commentDeleteLog(commentEventList.stream()
				.map(commentDtoMapper::toCommentCreateEventDto)
				.collect(Collectors.toList())); // 배치 처리
			log.info("comment delete log processing end");
			// Acknowledgment가 있으면 오프셋 커밋
			if (acknowledgment != null) {
				acknowledgment.acknowledge();
			}
		} catch (Exception e) {
			log.error("Message processing failed: {} ", e);
			// 실패 시 acknowledgment를 호출하지 않음 -> 재시도 가능
		}
	}

	@KafkaListener(topics = "${reply.create}", groupId = "${group-id.batch}", containerFactory = "replyBatchListenerContainerFactory")
	public void consumeReplyEvents(List<ReplyEvent> replyEventList, Acknowledgment acknowledgment) {
		try {
			log.info("reply create log processing start");
			commentLogUseCase.replyCreateLog(replyEventList.stream()
				.map(commentDtoMapper::toReplyCreateEventDto)
				.collect(Collectors.toList())); // 배치 처리
			log.info("reply create log processing end");
			// Acknowledgment가 있으면 오프셋 커밋
			if (acknowledgment != null) {
				acknowledgment.acknowledge();
			}
		} catch (Exception e) {
			log.error("Message processing failed: {} ", e);
			// 실패 시 acknowledgment를 호출하지 않음 -> 재시도 가능
		}
	}

	@KafkaListener(topics = "${reply.delete}", groupId = "${group-id.batch}", containerFactory = "replyBatchListenerContainerFactory")
	public void consumeReplyDeleteEvents(List<ReplyEvent> replyEventList, Acknowledgment acknowledgment) {
		try {
			log.info("reply delete log processing start");
			commentLogUseCase.replyDeleteLog(replyEventList.stream()
				.map(commentDtoMapper::toReplyCreateEventDto)
				.collect(Collectors.toList())); // 배치 처리
			log.info("reply delete log processing end");
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