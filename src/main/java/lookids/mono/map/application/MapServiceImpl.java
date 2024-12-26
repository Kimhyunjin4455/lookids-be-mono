package lookids.mono.map.application;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.common.entity.BaseResponseStatus;
import lookids.mono.common.exception.BaseException;
import lookids.mono.map.domain.Map;
import lookids.mono.map.dto.in.KafkaFeedDeleteRequestDto;
import lookids.mono.map.dto.in.PinReadDto;
import lookids.mono.map.dto.in.PinRequestDto;
import lookids.mono.map.dto.in.PinUpdateDto;
import lookids.mono.map.dto.out.DetailPinResponseDto;
import lookids.mono.map.dto.out.FeedCodeResponseDto;
import lookids.mono.map.dto.out.PinResponseDto;
import lookids.mono.map.infrastructure.MapRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MapServiceImpl implements MapService {

	private final MapRepository mapRepository;

	// 비동기 작업 관리를 위한 ConcurrentHashMap
	private final ConcurrentHashMap<String, CompletableFuture<String>> mediaUrlFuture = new ConcurrentHashMap<>();

	@Override
	public void createPin(PinRequestDto pinRequestDto) {
		CompletableFuture<String> responseFuture = new CompletableFuture<>();

		// CompletableFuture를 미디어와 연결하여 저장
		mediaUrlFuture.put(pinRequestDto.getUuid(), responseFuture);

		// 피드 코드를 수신한 후 저장 로직 처리
		// ToDo: 예외 처리 추가
		responseFuture.thenAccept(feedCode -> {
			mapRepository.save(pinRequestDto.toEntity(feedCode));
		}).exceptionally(e -> {
			return null;
		});

	}

	@Override
	public void createBasicPin(PinRequestDto pinRequestDto) {
		mapRepository.save(pinRequestDto.toEntity(null));
	}

	@Override
	public List<PinResponseDto> readPin(String uuid, PinReadDto pinReadDto) {

		return mapRepository.findPinsByArea(uuid, pinReadDto.getHa(), pinReadDto.getOa(), pinReadDto.getPa(),
			pinReadDto.getQa()).stream().map(PinResponseDto::toDto).toList();
	}

	@Override
	public List<PinResponseDto> readBasicPin(String category, PinReadDto pinReadDto) {

		return mapRepository.findBasicPinsByArea(category, pinReadDto.getHa(), pinReadDto.getOa(), pinReadDto.getPa(),
			pinReadDto.getQa()).stream().map(PinResponseDto::toDto).toList();
	}

	@Override
	public DetailPinResponseDto readDetailPin(String pinCode) {

		Map map = mapRepository.findByPinCode(pinCode)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PIN));

		return DetailPinResponseDto.toDto(map);
	}

	@Override
	public void updatePin(PinUpdateDto pinUpdateDto) {

		Map map = mapRepository.findByPinCode(pinUpdateDto.getPinCode())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PIN));

		mapRepository.save(pinUpdateDto.toUpdateEntity(map));

	}

	@Override
	public void deletePin(String pinCode) {

		Map map = mapRepository.findByPinCode(pinCode)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_PIN));

		mapRepository.save(PinUpdateDto.toDeleteEntity(map));

	}

	@KafkaListener(topics = "feed-create", groupId = "map-group", containerFactory = "kafkaListenerContainerFactory")
	public void consumeFeedCode(FeedCodeResponseDto feedCodeResponseDto) {

		String uuid = feedCodeResponseDto.getUuid();

		// mediaUrlFuture에 저장된 CompletableFuture를 찾아 feedCode를 저장, 찾기 위해 특정한 키 값이 필요
		CompletableFuture<String> responseFuture = mediaUrlFuture.get(uuid);

		if (responseFuture != null) {
			responseFuture.complete(feedCodeResponseDto.getFeedCode()); // CompletableFuture에 feedCode 저장
			mediaUrlFuture.remove(uuid);
		}
	}

	@KafkaListener(topics = "feed-delete", groupId = "feeddelete-group", containerFactory = "FeedDeleteContainerFactory")
	public void consumeFeedCode(KafkaFeedDeleteRequestDto kafkaFeedDeleteRequestDto) {
		String feedCode = kafkaFeedDeleteRequestDto.getFeedCode();
		mapRepository.deleteByFeedCode(feedCode);
	}
}
