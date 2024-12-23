package lookids.batch.follow.adaptor.out.infrastructure.mysql;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lookids.batch.follow.adaptor.out.infrastructure.entity.FollowCountEntity;
import lookids.batch.follow.adaptor.out.infrastructure.entity.FollowLogEntity;
import lookids.batch.follow.adaptor.out.infrastructure.mapper.FollowEntityMapper;
import lookids.batch.follow.application.port.dto.FollowBatchDto;
import lookids.batch.follow.application.port.dto.FollowCountSaveDto;
import lookids.batch.follow.application.port.dto.FollowLogSaveDto;
import lookids.batch.follow.application.port.out.FollowRepositoryPort;
import lookids.batch.follow.domain.model.FollowCount;
import lookids.batch.follow.domain.model.FollowLog;

@Repository
@AllArgsConstructor
public class FollowRepositoryAdaptor implements FollowRepositoryPort {

	private final FollowEntityMapper followEntityMapper;
	private final FollowLogRepository followLogRepository;
	private final FollowCountRepository followCountRepository;

	public void createLog(List<FollowLogSaveDto> followLogSaveDtoList) {
		List<FollowLogEntity> followLogEntityList = followLogSaveDtoList.stream()
			.map(followEntityMapper::toFollowLogEntity)
			.toList();
		followLogRepository.saveAll(followLogEntityList);
	}

	@Override
	public FollowCount readFollowCount(String uuid) {
		FollowCountEntity followCountEntity = followCountRepository.findByUuid(uuid).orElse(null);
		if (followCountEntity == null) {
			return followEntityMapper.toNullFollowCount(uuid);
		}
		return followEntityMapper.toFollowCount(followCountEntity);
	}

	@Override
	public Page<FollowLog> findUnprocessedLogs(Pageable pageable) {
		Page<FollowLogEntity> favoriteLogEntityPage = followLogRepository.findByProcessedFalse(pageable);
		return favoriteLogEntityPage.map(followEntityMapper::toFollowLog);
	}

	@Override
	public void markLogsAsProcessed(List<FollowLogSaveDto> followLogSaveDtoList) {
		followLogRepository.saveAll(followLogSaveDtoList.stream().map(followEntityMapper::toFollowLogEntity).toList());
	}

	@Override
	public List<FollowCount> findFollowCountsByTargetKeys(List<FollowBatchDto> followBatchDtoList) {
		// 1. 필요한 UUID 수집
		List<String> uuids = followBatchDtoList.stream().map(FollowBatchDto::getUuid).collect(Collectors.toList());

		// 2. 대량 조회 (리포지토리 메서드 호출)
		List<FollowCountEntity> followCountEntityList = followCountRepository.findByUuidIn(uuids);

		// 3. FeedEntity -> FeedCount 변환
		return followCountEntityList.stream().map(followEntityMapper::toFollowCount).collect(Collectors.toList());
	}

	@Override
	public void updateFollowCounts(List<FollowCountSaveDto> favoriteCountSaveDtoList) {

		followCountRepository.saveAll(
			favoriteCountSaveDtoList.stream().map(followEntityMapper::toFollowCountEntity).toList());
	}

}
