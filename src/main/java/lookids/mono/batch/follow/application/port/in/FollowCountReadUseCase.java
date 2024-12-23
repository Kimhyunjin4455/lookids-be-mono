package lookids.batch.follow.application.port.in;

import lookids.batch.follow.application.port.dto.FollowCountResponseDto;

public interface FollowCountReadUseCase {

	FollowCountResponseDto readFollowCount(String uuid);
}
