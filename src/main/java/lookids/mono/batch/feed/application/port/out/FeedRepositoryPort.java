package lookids.mono.batch.feed.application.port.out;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lookids.mono.batch.feed.application.port.dto.FeedBatchDto;
import lookids.mono.batch.feed.application.port.dto.FeedCountSaveDto;
import lookids.mono.batch.feed.application.port.dto.FeedLogSaveDto;
import lookids.mono.batch.feed.domain.model.FeedCount;
import lookids.mono.batch.feed.domain.model.FeedLog;

public interface FeedRepositoryPort {
	void createLog(List<FeedLogSaveDto> feedLogSaveDtoList);

	FeedCount readFeedCount(String feedCode);

	Page<FeedLog> findUnprocessedLogs(Pageable pageable);

	void markLogsAsProcessed(List<FeedLogSaveDto> feedLogSaveDtoList);

	List<FeedCount> findFeedCountsByTargetKeys(List<FeedBatchDto> feedBatchDtoList);

	void updateFeedCounts(List<FeedCountSaveDto> favoriteCountSaveDtoList);
}
