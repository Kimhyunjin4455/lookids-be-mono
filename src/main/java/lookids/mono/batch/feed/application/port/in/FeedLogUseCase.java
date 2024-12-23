package lookids.batch.feed.application.port.in;

import java.util.List;

import lookids.batch.feed.application.port.dto.FeedCreateEventDto;
import lookids.batch.feed.application.port.dto.FeedDeleteEventDto;

public interface FeedLogUseCase {

	void feedCreateLog(List<FeedCreateEventDto> feedCreateEventDto);

	void feedDeleteLog(List<FeedDeleteEventDto> feedDeleteEventDtoList);
}
