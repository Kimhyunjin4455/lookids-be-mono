package lookids.mono.favorite.application;

import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.favorite.dto.FavoriteFeedDto;
import lookids.mono.favorite.dto.FavoriteResponseDto;
import lookids.mono.favorite.dto.FeedKafkaRequestDto;

@Service
@RequiredArgsConstructor
@Slf4j
public class favoriteKafkaListener {

	private final FavoriteService favoriteService;
	private final KafkaTemplate<String, FavoriteFeedDto> kafkaTemplate;

	@KafkaListener(topics = "favorite-request", groupId = "favorite-join-feed", containerFactory = "feedEventListenerContainerFactory")
	public void consumeFeed(FeedKafkaRequestDto requestDto) {
		String uuid = requestDto.getUuid(); // UUID 추출
		List<FavoriteResponseDto> responseDtos = favoriteService.readUserFavoriteList(uuid);
		FavoriteFeedDto favoriteFeedDto = FavoriteFeedDto.builder()
			.uuid(uuid)
			.targetCodeList(responseDtos.stream().map(FavoriteResponseDto::getTargetCode).toList())
			.build();
		sendMessage("favorite-response", favoriteFeedDto);
	}

	public void sendMessage(String topic, FavoriteFeedDto favoriteFeedDto) {
		kafkaTemplate.send(topic, favoriteFeedDto);
	}
}
