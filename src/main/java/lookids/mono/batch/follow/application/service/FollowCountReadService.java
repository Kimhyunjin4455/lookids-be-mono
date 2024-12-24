package lookids.mono.batch.follow.application.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.batch.follow.application.mapper.FollowDtoMapper;
import lookids.mono.batch.follow.application.port.dto.FollowCountResponseDto;
import lookids.mono.batch.follow.application.port.in.FollowCountReadUseCase;
import lookids.mono.batch.follow.application.port.out.FollowRepositoryPort;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowCountReadService implements FollowCountReadUseCase {
	private final FollowDtoMapper followDtoMapper;
	private final FollowRepositoryPort followRepositoryPort;

	@Override
	public FollowCountResponseDto readFollowCount(String uuid) {
		return followDtoMapper.toFollowCountResponseDto(followRepositoryPort.readFollowCount(uuid));
	}

}
