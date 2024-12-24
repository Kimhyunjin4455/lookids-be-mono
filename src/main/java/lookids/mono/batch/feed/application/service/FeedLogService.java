package lookids.mono.batch.feed.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lookids.mono.batch.feed.application.mapper.FeedDtoMapper;
import lookids.mono.batch.feed.application.port.dto.FeedCreateEventDto;
import lookids.mono.batch.feed.application.port.dto.FeedDeleteEventDto;
import lookids.mono.batch.feed.application.port.in.FeedLogUseCase;
import lookids.mono.batch.feed.application.port.out.FeedRepositoryPort;
import lookids.mono.batch.feed.domain.model.FeedLog;

@Service
@RequiredArgsConstructor
public class FeedLogService implements FeedLogUseCase {

	private final FeedDtoMapper feedDtoMapper;
	private final FeedRepositoryPort feedRepositoryPort;

	@Override
	public void feedCreateLog(List<FeedCreateEventDto> feedCreateEventDtoList) {
		List<FeedLog> feedLogList = feedCreateEventDtoList.stream()
			.map(feedCreateEventDto -> FeedLog.builder()
				.feedCode(feedCreateEventDto.getFeedCode())
				.uuid(feedCreateEventDto.getUuid())
				.petCode(feedCreateEventDto.getPetCode())
				.createdAt(feedCreateEventDto.getCreatedAt())
				.logType("create")
				.processed(false)
				.build())
			.toList();
		feedRepositoryPort.createLog(feedLogList.stream().map(feedDtoMapper::toFeedLogSaveDto).toList());
	}

	@Override
	public void feedDeleteLog(List<FeedDeleteEventDto> feedDeleteEventDtoList) {
		List<FeedLog> feedLogList = feedDeleteEventDtoList.stream()
			.map(feedDeleteEventDto -> FeedLog.builder()
				.feedCode(feedDeleteEventDto.getFeedCode())
				.uuid(feedDeleteEventDto.getUuid())
				.createdAt(feedDeleteEventDto.getCreatedAt())
				.logType("delete")
				.processed(false)
				.build())
			.toList();
		feedRepositoryPort.createLog(feedLogList.stream().map(feedDtoMapper::toFeedLogSaveDto).toList());
	}
}
