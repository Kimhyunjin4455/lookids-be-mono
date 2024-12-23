package lookids.batch.follow.application.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.batch.follow.application.mapper.FollowDtoMapper;
import lookids.batch.follow.application.port.dto.FollowBatchDto;
import lookids.batch.follow.application.port.in.FollowBatchUseCase;
import lookids.batch.follow.application.port.out.FollowRepositoryPort;
import lookids.batch.follow.domain.model.FollowCount;
import lookids.batch.follow.domain.model.FollowLog;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowBatchService implements FollowBatchUseCase {
	private final FollowDtoMapper followDtoMapper;
	private final FollowRepositoryPort followRepositoryPort;
	private static final int BATCH_SIZE = 500;

	@Override
	@Transactional
	public void batchFollowCount() {
		int page = 0;

		while (true) {
			Pageable pageable = PageRequest.of(page, BATCH_SIZE);
			Page<FollowLog> followLogPage = followRepositoryPort.findUnprocessedLogs(pageable);

			if (followLogPage.isEmpty()) {
				break;
			}

			Map<String, Integer> senderMap = followLogPage.stream()
				.collect(Collectors.groupingBy(FollowLog::getSenderUuid,
					Collectors.summingInt(log -> log.getLogType().equals("create") ? 1 : -1)));
			Map<String, Integer> receiverMap = followLogPage.stream()
				.collect(Collectors.groupingBy(FollowLog::getReceiverUuid,
					Collectors.summingInt(log -> log.getLogType().equals("create") ? 1 : -1)));
			// senderMap.forEach((key, value) -> log.info("senderKey: {}, Value: {}", key, value));
			// receiverMap.forEach((key, value) -> log.info("receiverKey: {}, Value: {}", key, value));

			List<FollowBatchDto> followBatchDtoList = new ArrayList<>();

			// 모든 UUID를 수집 (senderMap과 receiverMap의 키를 합집합)
			Set<String> allUuids = new HashSet<>();
			allUuids.addAll(senderMap.keySet());
			allUuids.addAll(receiverMap.keySet());

			// UUID를 기준으로 FollowBatchDto 생성
			for (String uuid : allUuids) {
				Integer followingCount = senderMap.getOrDefault(uuid, 0); // followingCount는 senderMap 값
				Integer followerCount = receiverMap.getOrDefault(uuid, 0); // followerCount는 receiverMap 값
				FollowBatchDto dto = FollowBatchDto.builder()
					.uuid(uuid)
					.followingCount(followingCount)
					.followerCount(followerCount)
					.build();
				followBatchDtoList.add(dto);
			}

			// 결과 출력 (선택 사항)
			// followBatchDtoList.forEach(dto -> log.info("UUID: {}, FollowingCount: {}, FollowerCount: {}", dto.getUuid(),
			// 	dto.getFollowingCount(), dto.getFollowerCount()));

			List<FollowCount> followCountList = followRepositoryPort.findFollowCountsByTargetKeys(followBatchDtoList);

			Map<String, FollowCount> followCountMap = followCountList.stream()
				.collect(Collectors.toMap(FollowCount::getUuid, // 문자열로 결합하여 키 사용
					Function.identity()));

			// favoriteBatchKeyDtoList를 순회하면서 처리
			List<FollowCount> updateFollowCount = followBatchDtoList.stream().map(followBatchDto -> {

				// favoriteCountMap에서 해당 TargetKey에 대한 FavoriteCount 찾기
				FollowCount existingFollowCount = followCountMap.get(followBatchDto.getUuid());

				if (existingFollowCount != null) {
					// 만약 값이 있으면 그대로 사용
					return FollowCount.builder()
						.id(existingFollowCount.getId())
						.uuid(existingFollowCount.getUuid())
						.followingCount(existingFollowCount.getFollowingCount() + followBatchDto.getFollowingCount())
						.followerCount(existingFollowCount.getFollowerCount() + followBatchDto.getFollowerCount())
						.updateAt(LocalDateTime.now()) // 필요에 따라 다른 필드를 추가
						.build();
				} else {
					// 값이 없으면 새로운 FavoriteCount 빌더를 만들어서 반환
					return FollowCount.builder()
						.uuid(followBatchDto.getUuid())
						.followingCount(followBatchDto.getFollowingCount())
						.followerCount(followBatchDto.getFollowerCount())
						.updateAt(LocalDateTime.now()) // 필요에 따라 다른 필드를 추가
						.build();
				}
			}).toList();

			//log.info("updateFollowCount size:{}", updateFollowCount.size());

			// 4번 로직: 업데이트된 FavoriteCount 리스트를 한 번에 DB에 저장
			followRepositoryPort.updateFollowCounts(
				updateFollowCount.stream().map(followDtoMapper::toFollowCountSaveDto).collect(Collectors.toList()));

			// 로그 처리 완료 표시
			followRepositoryPort.markLogsAsProcessed(
				followLogPage.stream().map(followDtoMapper::toFollowLogUpdateDto).collect(Collectors.toList()));

			page++;
		}

	}
}

