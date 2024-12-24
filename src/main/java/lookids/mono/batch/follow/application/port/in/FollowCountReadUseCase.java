package lookids.mono.batch.follow.application.port.in;

import lookids.mono.batch.follow.application.port.dto.FollowCountResponseDto;

public interface FollowCountReadUseCase {

	FollowCountResponseDto readFollowCount(String uuid);
}
