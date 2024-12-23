package Lookids.Feed.feed.application;

import Lookids.Feed.feed.dto.in.FeedRequestDto;

public interface FeedService {

    void createFeed(FeedRequestDto feedRequestDto);
    void deleteFeed(String uuid, String feedCode);
}
