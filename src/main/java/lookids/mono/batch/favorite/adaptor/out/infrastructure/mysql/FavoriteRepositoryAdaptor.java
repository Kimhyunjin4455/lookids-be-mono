package lookids.mono.batch.favorite.adaptor.out.infrastructure.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.batch.favorite.adaptor.out.infrastructure.entity.FavoriteCountEntity;
import lookids.mono.batch.favorite.adaptor.out.infrastructure.entity.FavoriteLogEntity;
import lookids.mono.batch.favorite.adaptor.out.infrastructure.mapper.FavoriteEntityMapper;
import lookids.mono.batch.favorite.application.port.dto.FavoriteBatchDto;
import lookids.mono.batch.favorite.application.port.dto.FavoriteCountSaveDto;
import lookids.mono.batch.favorite.application.port.dto.FavoriteLogSaveDto;
import lookids.mono.batch.favorite.application.port.out.FavoriteRepositoryPort;
import lookids.mono.batch.favorite.domain.FavoriteType;
import lookids.mono.batch.favorite.domain.model.FavoriteCount;
import lookids.mono.batch.favorite.domain.model.FavoriteLog;

@Slf4j
@Repository
@AllArgsConstructor
public class FavoriteRepositoryAdaptor implements FavoriteRepositoryPort {

	private final FavoriteLogRepository favoriteLogRepository;

	private final FavoriteCountRepository favoriteCountRepository;

	private final FavoriteEntityMapper favoriteEntityMapper;

	@Override
	public void createLog(FavoriteLogSaveDto favoriteLogSaveDto) {
		favoriteLogRepository.save(favoriteEntityMapper.toFavoriteLogEntity(favoriteLogSaveDto));
	}

	@Override
	public FavoriteCount readFavoriteCount(String targetCode, FavoriteType type) {
		// Repository에서 데이터를 조회
		FavoriteCountEntity favoriteCountEntity = favoriteCountRepository.findByTargetCodeAndType(targetCode, type)
			.orElse(null);

		// null일 경우 기본값 처리
		if (favoriteCountEntity == null) {
			return favoriteEntityMapper.toNullFavoriteCount(targetCode, type);
		}

		// 값이 있을 경우 매핑
		return favoriteEntityMapper.toFavoriteCount(favoriteCountEntity);
	}

	@Override
	public Page<FavoriteLog> findUnprocessedLogs(Pageable pageable) {
		Page<FavoriteLogEntity> favoriteLogEntityPage = favoriteLogRepository.findByProcessedFalse(pageable);
		return favoriteLogEntityPage.map(favoriteEntityMapper::toFavoriteLog);
	}

	@Override
	public void markLogsAsProcessed(List<FavoriteLogSaveDto> favoriteLogSaveDtoList) {
		log.info("first:{}", favoriteLogSaveDtoList.get(0).getId());
		favoriteLogRepository.saveAll(
			favoriteLogSaveDtoList.stream().map(favoriteEntityMapper::toFavoriteLogEntity).toList());
	}

	@Override
	public List<FavoriteCount> findFavoriteCountsByTargetKeys(List<FavoriteBatchDto> favoriteBatchDtoList) {
		// return favoriteBatchDtoList.stream()
		// 	.map(favoriteBatchKeyDto -> favoriteCountRepository.findByTargetCodeAndType(
		// 		favoriteBatchKeyDto.getTargetCode(), favoriteBatchKeyDto.getFavoriteType()))
		// 	.filter(Optional::isPresent)  // Optional을 필터링하여 null을 걸러냄
		// 	.map(Optional::get)  // Optional에서 실제 값 추출
		// 	.map(favoriteEntityMapper::toFavoriteCount)  // 엔티티를 FavoriteCount로 변환
		// 	.collect(Collectors.toList());  // 최종 리스트로 수집

		// 1. 필요한 targetCode와 favoriteType 수집
		Map<String, FavoriteType> targetCodeAndTypeMap = favoriteBatchDtoList.stream()
			.collect(Collectors.toMap(FavoriteBatchDto::getTargetCode, FavoriteBatchDto::getFavoriteType));

		// 2. 대량 조회 쿼리 (리포지토리 메서드에서 IN 조건 활용)
		List<FavoriteCountEntity> favoriteCountEntityList = favoriteCountRepository.findByTargetCodeInAndTypeIn(
			new ArrayList<>(targetCodeAndTypeMap.keySet()), new ArrayList<>(targetCodeAndTypeMap.values()));

		// 3. FavoriteEntity -> FavoriteCount 변환
		return favoriteCountEntityList.stream().map(favoriteEntityMapper::toFavoriteCount).collect(Collectors.toList());
	}

	@Override
	public void updateFavoriteCounts(List<FavoriteCountSaveDto> favoriteCountSaveDtoList) {
		log.info("first:{}", favoriteCountSaveDtoList.get(0).getId());
		favoriteCountRepository.saveAll(
			favoriteCountSaveDtoList.stream().map(favoriteEntityMapper::toFavoriteCountEntity).toList());
	}
}
