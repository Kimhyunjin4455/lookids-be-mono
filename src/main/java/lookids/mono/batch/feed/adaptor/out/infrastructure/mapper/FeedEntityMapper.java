package lookids.mono.batch.feed.adaptor.out.infrastructure.mapper;

import org.springframework.stereotype.Component;

import lookids.mono.batch.feed.adaptor.out.infrastructure.entity.FeedCountEntity;
import lookids.mono.batch.feed.adaptor.out.infrastructure.entity.FeedLogEntity;
import lookids.mono.batch.feed.application.port.dto.FeedCountSaveDto;
import lookids.mono.batch.feed.application.port.dto.FeedLogSaveDto;
import lookids.mono.batch.feed.domain.model.FeedCount;
import lookids.mono.batch.feed.domain.model.FeedLog;

@Component
public class FeedEntityMapper {

	public FeedLogEntity toFeedLogEntity(FeedLogSaveDto feedLogSaveDto) {
		return FeedLogEntity.builder()
			.id(feedLogSaveDto.getId())
			.feedCode(feedLogSaveDto.getFeedCode())
			.uuid(feedLogSaveDto.getUuid())
			.petCode(feedLogSaveDto.getPetCode())
			.logType(feedLogSaveDto.getLogType())
			.createdAt(feedLogSaveDto.getCreatedAt())
			.processed(feedLogSaveDto.getProcessed())
			.build();
	}

	public FeedLog toFeedLog(FeedLogEntity feedLogEntity) {
		return FeedLog.builder()
			.id(feedLogEntity.getId())
			.feedCode(feedLogEntity.getFeedCode())
			.uuid(feedLogEntity.getUuid())
			//.petCode(feedLogEntity.getPetCode())
			.logType(feedLogEntity.getLogType())
			.createdAt(feedLogEntity.getCreatedAt())
			.processed(feedLogEntity.getProcessed())
			.build();
	}

	public FeedCount toFeedCount(FeedCountEntity feedCountEntity) {
		return FeedCount.builder()
			.id(feedCountEntity.getId())
			.uuid(feedCountEntity.getUuid())
			.count(feedCountEntity.getCount())
			.updateAt(feedCountEntity.getUpdateAt())
			.build();
	}

	public FeedCount toNullFeedCount(String uuid) {
		return FeedCount.builder().uuid(uuid).count(0).build();
	}

	public FeedCountEntity toFeedCountEntity(FeedCountSaveDto feedCountSaveDto) {
		return FeedCountEntity.builder()
			.id(feedCountSaveDto.getId())
			.uuid(feedCountSaveDto.getUuid())
			.count(feedCountSaveDto.getCount())
			.updateAt(feedCountSaveDto.getUpdateAt())
			.build();
	}
}
