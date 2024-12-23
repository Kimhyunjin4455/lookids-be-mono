package lookids.batch.feed.application.mapper;

import org.springframework.stereotype.Component;

import lookids.batch.feed.adaptor.in.kafka.event.FeedDeleteEvent;
import lookids.batch.feed.adaptor.in.kafka.event.FeedEvent;
import lookids.batch.feed.application.port.dto.FeedBatchDto;
import lookids.batch.feed.application.port.dto.FeedCountResponseDto;
import lookids.batch.feed.application.port.dto.FeedCountSaveDto;
import lookids.batch.feed.application.port.dto.FeedCreateEventDto;
import lookids.batch.feed.application.port.dto.FeedDeleteEventDto;
import lookids.batch.feed.application.port.dto.FeedLogSaveDto;
import lookids.batch.feed.domain.model.FeedCount;
import lookids.batch.feed.domain.model.FeedLog;

@Component
public class FeedDtoMapper {

	public FeedCreateEventDto toFeedCreateEventDto(FeedEvent feedEvent) {
		return FeedCreateEventDto.builder()
			.feedCode(feedEvent.getFeedCode())
			.uuid(feedEvent.getUuid())
			.petCode(feedEvent.getPetCode())
			.createdAt(feedEvent.getCreatedAt())
			.build();
	}

	public FeedDeleteEventDto toFeedDeleteEventDto(FeedDeleteEvent feedDeleteEvent) {
		return FeedDeleteEventDto.builder()
			.feedCode(feedDeleteEvent.getFeedCode())
			.uuid(feedDeleteEvent.getUuid())
			.createdAt(feedDeleteEvent.getCreatedAt())
			.build();
	}

	public FeedLogSaveDto toFeedLogSaveDto(FeedLog feedLog) {
		return FeedLogSaveDto.builder()
			.id(feedLog.getId())
			.feedCode(feedLog.getFeedCode())
			.uuid(feedLog.getUuid())
			.petCode(feedLog.getPetCode())
			.createdAt(feedLog.getCreatedAt())
			.logType(feedLog.getLogType())
			.processed(feedLog.getProcessed())
			.build();
	}

	public FeedCountResponseDto toFeedCountResponseDto(FeedCount feedCount) {
		return FeedCountResponseDto.builder().count(feedCount.getCount()).build();
	}

	public FeedBatchDto toFeedBatchDto(String uuid, Integer count) {
		return FeedBatchDto.builder().uuid(uuid).count(count).build();
	}

	public FeedCountSaveDto toFeedCountSaveDto(FeedCount feedCount) {
		return FeedCountSaveDto.builder()
			.id(feedCount.getId())
			.uuid(feedCount.getUuid())
			.count(feedCount.getCount())
			.updateAt(feedCount.getUpdateAt())
			.build();
	}

	public FeedLogSaveDto toFeedLogUpdateDto(FeedLog feedLog) {
		return FeedLogSaveDto.builder()
			.id(feedLog.getId())
			.feedCode(feedLog.getFeedCode())
			.uuid(feedLog.getUuid())
			.petCode(feedLog.getPetCode())
			.createdAt(feedLog.getCreatedAt())
			.logType(feedLog.getLogType())
			.processed(true)
			.build();
	}
}
