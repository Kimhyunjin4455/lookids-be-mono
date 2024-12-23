package lookids.batch.feed.application.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.batch.feed.application.mapper.FeedDtoMapper;
import lookids.batch.feed.application.port.dto.FeedCountResponseDto;
import lookids.batch.feed.application.port.in.FeedCountReadUseCase;
import lookids.batch.feed.application.port.out.FeedRepositoryPort;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedCountReadService implements FeedCountReadUseCase {
	private final FeedDtoMapper feedDtoMapper;
	private final FeedRepositoryPort feedRepositoryPort;

	@Override
	public FeedCountResponseDto readFeedCount(String uuid) {
		return feedDtoMapper.toFeedCountResponseDto(feedRepositoryPort.readFeedCount(uuid));
	}
}
