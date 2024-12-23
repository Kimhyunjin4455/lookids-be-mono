package lookids.batch.follow.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lookids.batch.follow.application.mapper.FollowDtoMapper;
import lookids.batch.follow.application.port.dto.FollowEventDto;
import lookids.batch.follow.application.port.in.FollowLogUseCase;
import lookids.batch.follow.application.port.out.FollowRepositoryPort;
import lookids.batch.follow.domain.model.FollowLog;

@Service
@RequiredArgsConstructor
public class FollowLogService implements FollowLogUseCase {
	private final FollowDtoMapper followDtoMapper;
	private final FollowRepositoryPort followRepositoryPort;

	@Override
	public void followCreateLog(List<FollowEventDto> followEventDtoList) {
		List<FollowLog> followLogList = followEventDtoList.stream()
			.map(followEventDto -> FollowLog.builder()
				.senderUuid(followEventDto.getSenderUuid())
				.receiverUuid(followEventDto.getReceiverUuid())
				.logType("create")
				.createdAt(followEventDto.getCreatedAt())
				.processed(false)
				.build())
			.toList();
		followRepositoryPort.createLog(followLogList.stream().map(followDtoMapper::toFollowLogSaveDto).toList());
	}

	@Override
	public void followDeleteLog(List<FollowEventDto> followEventDtoList) {
		List<FollowLog> followLogList = followEventDtoList.stream()
			.map(followEventDto -> FollowLog.builder()
				.senderUuid(followEventDto.getSenderUuid())
				.receiverUuid(followEventDto.getReceiverUuid())
				.logType("delete")
				.createdAt(followEventDto.getCreatedAt())
				.processed(false)
				.build())
			.toList();
		followRepositoryPort.createLog(followLogList.stream().map(followDtoMapper::toFollowLogSaveDto).toList());
	}
}
