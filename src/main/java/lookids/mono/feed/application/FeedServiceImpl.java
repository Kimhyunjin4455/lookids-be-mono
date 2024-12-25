package lookids.mono.feed.application;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.common.entity.BaseResponseStatus;
import lookids.mono.common.exception.BaseException;
import lookids.mono.feed.domain.Feed;
import lookids.mono.feed.dto.in.DeleteKafkaDto;
import lookids.mono.feed.dto.in.FeedKafkaDto;
import lookids.mono.feed.dto.in.FeedRequestDto;
import lookids.mono.feed.infrastructure.FeedRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

	private final FeedRepository feedRepository;
	private final KafkaTemplate<String, FeedKafkaDto> feedkafkaTemplate;
	private final KafkaTemplate<String, DeleteKafkaDto> deletekafkaTemplate;

	@Override
	public void createFeed(FeedRequestDto feedRequestDto) {
		Feed savefeed = feedRepository.save(feedRequestDto.toEntity());
		FeedKafkaDto feedKafkaDto = feedRequestDto.toDto(savefeed);
		feedkafkaTemplate.send("feed-create", feedKafkaDto);
	}

	@Override
	public void deleteFeed(String uuid, String feedCode) {
		Feed feed = feedRepository.findByFeedCodeAndStateTrue(feedCode)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_FEED));
		feedRepository.save(FeedRequestDto.toDelete(feed).toEntityForUpdate());
		DeleteKafkaDto deleteKafkaDto = DeleteKafkaDto.toDto(feedCode, uuid);
		deletekafkaTemplate.send("feed-delete", deleteKafkaDto);
	}
}
