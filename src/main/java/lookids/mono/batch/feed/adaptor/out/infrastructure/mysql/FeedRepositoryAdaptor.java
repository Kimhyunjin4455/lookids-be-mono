package lookids.mono.batch.feed.adaptor.out.infrastructure.mysql;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lookids.mono.batch.feed.adaptor.out.infrastructure.entity.FeedCountEntity;
import lookids.mono.batch.feed.adaptor.out.infrastructure.entity.FeedLogEntity;
import lookids.mono.batch.feed.adaptor.out.infrastructure.mapper.FeedEntityMapper;
import lookids.mono.batch.feed.application.port.dto.FeedBatchDto;
import lookids.mono.batch.feed.application.port.dto.FeedCountSaveDto;
import lookids.mono.batch.feed.application.port.dto.FeedLogSaveDto;
import lookids.mono.batch.feed.application.port.out.FeedRepositoryPort;
import lookids.mono.batch.feed.domain.model.FeedCount;
import lookids.mono.batch.feed.domain.model.FeedLog;

@Repository
@AllArgsConstructor
public class FeedRepositoryAdaptor implements FeedRepositoryPort {
	private final FeedEntityMapper feedEntityMapper;

	private final FeedLogRepository feedLogRepository;
	private final FeedCountRepository feedCountRepository;

	@Override
	public void createLog(List<FeedLogSaveDto> feedLogSaveDtoList) {
		List<FeedLogEntity> feedLogEntityList = feedLogSaveDtoList.stream()
			.map(feedEntityMapper::toFeedLogEntity)
			.toList();
		feedLogRepository.saveAll(feedLogEntityList);
	}

	@Override
	public FeedCount readFeedCount(String uuid) {
		FeedCountEntity feedCountEntity = feedCountRepository.findByUuid(uuid).orElse(null);
		// null일 경우 기본값 처리
		if (feedCountEntity == null) {
			return feedEntityMapper.toNullFeedCount(uuid);
		}

		// 값이 있을 경우 매핑
		return feedEntityMapper.toFeedCount(feedCountEntity);
	}

	@Override
	public Page<FeedLog> findUnprocessedLogs(Pageable pageable) {
		Page<FeedLogEntity> favoriteLogEntityPage = feedLogRepository.findByProcessedFalse(pageable);
		return favoriteLogEntityPage.map(feedEntityMapper::toFeedLog);
	}

	@Override
	public void markLogsAsProcessed(List<FeedLogSaveDto> feedLogSaveDtoList) {
		feedLogRepository.saveAll(feedLogSaveDtoList.stream().map(feedEntityMapper::toFeedLogEntity).toList());
	}

	@Override
	public List<FeedCount> findFeedCountsByTargetKeys(List<FeedBatchDto> feedBatchDtoList) {
		// 1. 필요한 UUID 수집
		List<String> uuids = feedBatchDtoList.stream().map(FeedBatchDto::getUuid).collect(Collectors.toList());

		// 2. 대량 조회 (리포지토리 메서드 호출)
		List<FeedCountEntity> feedCountEntityList = feedCountRepository.findByUuidIn(uuids);

		// 3. FeedEntity -> FeedCount 변환
		return feedCountEntityList.stream().map(feedEntityMapper::toFeedCount).collect(Collectors.toList());
	}

	@Override
	public void updateFeedCounts(List<FeedCountSaveDto> favoriteCountSaveDtoList) {

		feedCountRepository.saveAll(
			favoriteCountSaveDtoList.stream().map(feedEntityMapper::toFeedCountEntity).toList());
	}

}
