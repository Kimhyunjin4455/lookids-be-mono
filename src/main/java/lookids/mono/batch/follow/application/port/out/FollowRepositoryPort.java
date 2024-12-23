package lookids.batch.follow.application.port.out;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lookids.batch.follow.application.port.dto.FollowBatchDto;
import lookids.batch.follow.application.port.dto.FollowCountSaveDto;
import lookids.batch.follow.application.port.dto.FollowLogSaveDto;
import lookids.batch.follow.domain.model.FollowCount;
import lookids.batch.follow.domain.model.FollowLog;

public interface FollowRepositoryPort {
	void createLog(List<FollowLogSaveDto> followLogSaveDtoList);

	FollowCount readFollowCount(String uuid);

	Page<FollowLog> findUnprocessedLogs(Pageable pageable);

	void markLogsAsProcessed(List<FollowLogSaveDto> followLogSaveDtoList);

	List<FollowCount> findFollowCountsByTargetKeys(List<FollowBatchDto> followBatchDtoList);

	void updateFollowCounts(List<FollowCountSaveDto> favoriteCountSaveDtoList);
}
