package lookids.batch.feed.application.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.batch.feed.application.mapper.FeedDtoMapper;
import lookids.batch.feed.application.port.dto.FeedBatchDto;
import lookids.batch.feed.application.port.in.FeedBatchUseCase;
import lookids.batch.feed.application.port.out.FeedRepositoryPort;
import lookids.batch.feed.domain.model.FeedCount;
import lookids.batch.feed.domain.model.FeedLog;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedBatchService implements FeedBatchUseCase {
	private final FeedDtoMapper feedDtoMapper;
	private final FeedRepositoryPort feedRepositoryPort;
	private static final int BATCH_SIZE = 500;

	@Override
	@Transactional
	public void batchFeedCount() {
		int page = 0;

		while (true) {
			Pageable pageable = PageRequest.of(page, BATCH_SIZE);
			Page<FeedLog> feedLogPage = feedRepositoryPort.findUnprocessedLogs(pageable);

			if (feedLogPage.isEmpty()) {
				break;
			}

			Map<String, Integer> aggregationMap = feedLogPage.stream()
				.collect(Collectors.groupingBy(FeedLog::getUuid,  // feedCode를 키로 사용
					Collectors.summingInt(log -> log.getLogType().equals("create") ? 1 : -1)));
			aggregationMap.forEach((key, value) -> log.info("Key: {}, Value: {}", key, value));

			// DB 갱신을 위한 DTO 리스트 변환
			List<FeedBatchDto> feedBatchDtoList = aggregationMap.keySet()
				.stream()
				.map(key -> feedDtoMapper.toFeedBatchDto(key, aggregationMap.get(key)))
				.collect(Collectors.toList());

			//log.info("favoriteBatchKeyDtoList size:{}", feedBatchDtoList.size());

			List<FeedCount> feedCountList = feedRepositoryPort.findFeedCountsByTargetKeys(feedBatchDtoList);

			//log.info("favoriteCountList size:{}", feedCountList.size());

			Map<String, FeedCount> feedCountMap = feedCountList.stream()
				.collect(Collectors.toMap(FeedCount::getUuid,  // 문자열로 결합하여 키 사용
					Function.identity()));

			List<FeedCount> updateFeedCount = feedBatchDtoList.stream().map(feedBatchDto -> {

				FeedCount existingFeedCount = feedCountMap.get(feedBatchDto.getUuid());

				if (existingFeedCount != null) {
					// 만약 값이 있으면 그대로 사용
					return FeedCount.builder()
						.id(existingFeedCount.getId())
						.uuid(existingFeedCount.getUuid())
						.count(existingFeedCount.getCount() + feedBatchDto.getCount())
						.updateAt(LocalDateTime.now())// 필요에 따라 다른 필드를 추가
						.build();
				} else {
					// 값이 없으면 새로운 FavoriteCount 빌더를 만들어서 반환
					return FeedCount.builder()
						.uuid(feedBatchDto.getUuid())
						.count(feedBatchDto.getCount())
						.updateAt(LocalDateTime.now())
						.build();
				}
			}).toList();

			// 4번 로직: 업데이트된 FavoriteCount 리스트를 한 번에 DB에 저장
			feedRepositoryPort.updateFeedCounts(
				updateFeedCount.stream().map(feedDtoMapper::toFeedCountSaveDto).collect(Collectors.toList()));

			// 로그 처리 완료 표시
			feedRepositoryPort.markLogsAsProcessed(
				feedLogPage.stream().map(feedDtoMapper::toFeedLogUpdateDto).collect(Collectors.toList()));

			page++;
		}

	}
}

