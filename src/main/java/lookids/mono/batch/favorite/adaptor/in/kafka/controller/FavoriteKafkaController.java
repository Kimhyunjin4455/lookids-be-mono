package lookids.batch.favorite.adaptor.in.kafka.controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.batch.favorite.adaptor.in.kafka.event.FavoriteEvent;
import lookids.batch.favorite.adaptor.in.kafka.mapper.FavoriteKafkaVoMapper;
import lookids.batch.favorite.application.mapper.FavoriteDtoMapper;
import lookids.batch.favorite.application.port.in.FavoriteLogUseCase;

@Slf4j
@RequiredArgsConstructor
@Component
public class FavoriteKafkaController {

	private final FavoriteLogUseCase favoriteLogUseCase;
	private final FavoriteDtoMapper favoriteDtoMapper;
	private final FavoriteKafkaVoMapper favoriteKafkaVoMapper;
	private final KafkaTemplate<String, String> favoritekafkaTemplate;

	@KafkaListener(topics = "${favorite.update}", groupId = "${group-id}", containerFactory = "favoriteEventListenerContainerFactory")
	public void consumeFavoriteEvents(FavoriteEvent favoriteEvent) {
		try {
			log.info("favorite start");

			favoriteLogUseCase.favoriteUpdate(favoriteDtoMapper.toFavoriteUpdateEventDto(favoriteEvent)); // 배치 처리
			log.info("favorite end");
		} catch (Exception e) {
			log.error("Message processing failed: {} ", e);
			// 실패 시 acknowledgment를 호출하지 않음 -> 재시도 가능
		}
	}
}