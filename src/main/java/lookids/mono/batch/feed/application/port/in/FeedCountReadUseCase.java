package lookids.batch.feed.application.port.in;

import lookids.batch.feed.application.port.dto.FeedCountResponseDto;

public interface FeedCountReadUseCase {
	FeedCountResponseDto readFeedCount(String uuid);

}
