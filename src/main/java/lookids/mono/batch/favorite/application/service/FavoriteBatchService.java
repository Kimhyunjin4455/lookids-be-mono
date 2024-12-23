package lookids.batch.favorite.application.service;

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
import lookids.batch.favorite.application.mapper.FavoriteDtoMapper;
import lookids.batch.favorite.application.port.dto.FavoriteBatchDto;
import lookids.batch.favorite.application.port.in.FavoriteBatchUseCase;
import lookids.batch.favorite.application.port.out.FavoriteRepositoryPort;
import lookids.batch.favorite.domain.model.FavoriteCount;
import lookids.batch.favorite.domain.model.FavoriteLog;
import lookids.batch.favorite.domain.model.TargetKey;

@Slf4j
@Service
@RequiredArgsConstructor
public class FavoriteBatchService implements FavoriteBatchUseCase {
	private final FavoriteDtoMapper favoriteDtoMapper;
	private final FavoriteRepositoryPort favoriteRepositoryPort;
	private static final int BATCH_SIZE = 500;

	@Override
	@Transactional
	public void batchFavoriteCount() {
		int page = 0;

		while (true) {
			Pageable pageable = PageRequest.of(page, BATCH_SIZE);
			Page<FavoriteLog> favoriteLogPage = favoriteRepositoryPort.findUnprocessedLogs(pageable);

			if (favoriteLogPage.isEmpty()) {
				break;
			}

			// 로그 데이터 집계
			Map<TargetKey, Integer> aggregationMap = favoriteLogPage.stream()
				.collect(Collectors.groupingBy(log -> new TargetKey(log.getTargetCode(), log.getFavoriteType()),
					Collectors.summingInt(log -> log.getLogType().equals("create") ? 1 : -1)));

			aggregationMap.forEach((key, value) -> log.info("Key: {}, Value: {}", key, value));

			// DB 갱신을 위한 DTO 리스트 변환
			List<FavoriteBatchDto> favoriteBatchDtoList = aggregationMap.keySet()
				.stream()
				.map(key -> favoriteDtoMapper.toFavoriteBatchDto(key, aggregationMap.get(key)))
				.collect(Collectors.toList());

			log.info("favoriteBatchKeyDtoList size:{}", favoriteBatchDtoList.size());

			List<FavoriteCount> favoriteCountList = favoriteRepositoryPort.findFavoriteCountsByTargetKeys(
				favoriteBatchDtoList);

			log.info("favoriteCountList size:{}", favoriteCountList.size());

			// favoriteCountList를 TargetKey 기준으로 맵으로 변환
			Map<TargetKey, FavoriteCount> favoriteCountMap = favoriteCountList.stream()
				.collect(Collectors.toMap(fc -> new TargetKey(fc.getTargetCode(), fc.getType()),  // TargetKey를 키로 사용
					Function.identity()));

			// favoriteBatchKeyDtoList를 순회하면서 처리
			List<FavoriteCount> updateFavoriteCount = favoriteBatchDtoList.stream().map(favoriteBatchKeyDto -> {
				// TargetKey 생성
				TargetKey targetKey = new TargetKey(favoriteBatchKeyDto.getTargetCode(),
					favoriteBatchKeyDto.getFavoriteType());

				// favoriteCountMap에서 해당 TargetKey에 대한 FavoriteCount 찾기
				FavoriteCount existingFavoriteCount = favoriteCountMap.get(targetKey);

				if (existingFavoriteCount != null) {
					// 만약 값이 있으면 그대로 사용
					return FavoriteCount.builder()
						.id(existingFavoriteCount.getId())
						.targetCode(existingFavoriteCount.getTargetCode())
						.type(existingFavoriteCount.getType())
						.count(existingFavoriteCount.getCount() + favoriteBatchKeyDto.getCount())
						.updateAt(LocalDateTime.now())// 필요에 따라 다른 필드를 추가
						.build();
				} else {
					// 값이 없으면 새로운 FavoriteCount 빌더를 만들어서 반환
					return FavoriteCount.builder()
						.targetCode(favoriteBatchKeyDto.getTargetCode())
						.type(favoriteBatchKeyDto.getFavoriteType())
						.count(favoriteBatchKeyDto.getCount()) // 필요에 따라 다른 필드를 추가
						.updateAt(LocalDateTime.now())
						.build();
				}
			}).toList();

			// 4번 로직: 업데이트된 FavoriteCount 리스트를 한 번에 DB에 저장
			favoriteRepositoryPort.updateFavoriteCounts(updateFavoriteCount.stream()
				.map(favoriteDtoMapper::toFavoriteCountSaveDto)
				.collect(Collectors.toList()));

			// 로그 처리 완료 표시
			favoriteRepositoryPort.markLogsAsProcessed(
				favoriteLogPage.stream().map(favoriteDtoMapper::toFavoriteLogUpdateDto).collect(Collectors.toList()));

			page++;
		}
	}
}