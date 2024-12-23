package lookids.batch.follow.application.port.in;

import java.util.List;

import lookids.batch.follow.application.port.dto.FollowEventDto;

public interface FollowLogUseCase {

	void followCreateLog(List<FollowEventDto> followEventDtoList);

	void followDeleteLog(List<FollowEventDto> followEventDtoList);
}
