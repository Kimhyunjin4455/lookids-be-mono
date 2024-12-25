package lookids.mono.feed.application;

import lookids.mono.feed.dto.in.FeedRequestDto;

public interface FeedService {

	void createFeed(FeedRequestDto feedRequestDto);

	void deleteFeed(String uuid, String feedCode);
}
