package lookids.favorite.favorite.application;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.favorite.common.kafka.dto.out.FavoriteBatchDto;
import lookids.favorite.common.kafka.dto.out.FavoriteNotificationDto;
import lookids.favorite.favorite.domain.Favorite;
import lookids.favorite.favorite.domain.FavoriteType;
import lookids.favorite.favorite.dto.FavoriteRequestDto;
import lookids.favorite.favorite.dto.FavoriteResponseDto;
import lookids.favorite.favorite.infrastructure.FavoriteRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
	private final FavoriteRepository favoriteRepository;
	private final KafkaTemplate<String, FavoriteBatchDto> kafkaTemplateForBatch;
	private final KafkaTemplate<String, FavoriteNotificationDto> kafkaTemplateForNotification;

	@Override
	public void createFavorite(FavoriteRequestDto favoriteRequestDto) {
		Favorite saveFavorite = favoriteRepository.save(favoriteRequestDto.toEntity());

		kafkaTemplateForBatch.send("favorite-update", FavoriteBatchDto.toDto(saveFavorite)); //배치용

		if (favoriteRequestDto.getFavoriteType() == FavoriteType.FEED) {
			kafkaTemplateForNotification.send("feed-favorite-create",
				FavoriteNotificationDto.toDto(favoriteRequestDto)); //알림용
		} else {
			kafkaTemplateForNotification.send("comment-favorite-create",
				FavoriteNotificationDto.toDto(favoriteRequestDto)); //알림용
		}

	}

	@Override
	public void updateFavorite(FavoriteRequestDto favoriteRequestDto) {
		Favorite favorite = favoriteRepository.findByTargetCodeAndUuid(favoriteRequestDto.getTargetCode(),
			favoriteRequestDto.getUuid()).orElse(null);

		if (favorite == null) {
			createFavorite(favoriteRequestDto);
		} else {
			Favorite saveFavorite = favoriteRepository.save(FavoriteRequestDto.toUpdateEntity(favorite));
			kafkaTemplateForBatch.send("favorite-update", FavoriteBatchDto.toDto(saveFavorite));
		}

	}

	@Override
	public List<FavoriteResponseDto> readUserFavoriteList(String uuid) {
		return favoriteRepository.findByUuidAndFavoriteStateTrue(uuid)
			.stream()
			.map(FavoriteResponseDto::toDto)
			.toList();
	}

	@Override
	public Page<FavoriteResponseDto> readFeedFavoriteList(String targetCode, FavoriteType favoriteType, int page,
		int size) {
		Pageable pageable = PageRequest.of(page, size);

		return favoriteRepository.findByTargetCodeAndFavoriteTypeAndFavoriteStateTrue(
			targetCode, favoriteType, pageable
		).map(FavoriteResponseDto::toDto);
	}

	@Override
	public Boolean readFavorite(String uuid, String targetCode) {
		Favorite favorite = favoriteRepository.findByUuidAndTargetCodeAndFavoriteStateTrue(uuid, targetCode)
			.orElse(null);
		if (favorite == null) {
			return false;
		}
		return true;
	}

}
