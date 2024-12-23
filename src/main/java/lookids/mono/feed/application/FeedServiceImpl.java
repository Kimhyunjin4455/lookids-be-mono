package Lookids.Feed.feed.application;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import Lookids.Feed.common.entity.BaseResponseStatus;
import Lookids.Feed.common.exception.BaseException;
import Lookids.Feed.feed.domain.Feed;
import Lookids.Feed.feed.dto.in.FeedRequestDto;
import Lookids.Feed.feed.dto.in.FeedKafkaDto;
import Lookids.Feed.feed.dto.in.DeleteKafkaDto;
import Lookids.Feed.feed.infrastructure.FeedRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    private final FeedRepository feedRepository;
    private final KafkaTemplate<String, FeedKafkaDto> feedkafkaTemplate;
    private final KafkaTemplate<String, DeleteKafkaDto> deletekafkaTemplate;

    @Override
    public void createFeed(FeedRequestDto feedRequestDto){
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
