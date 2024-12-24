package lookids.mono.batch.feed.application.port.in;

import lookids.mono.batch.feed.application.port.dto.FeedCountResponseDto;

public interface FeedCountReadUseCase {
	FeedCountResponseDto readFeedCount(String uuid);

}
