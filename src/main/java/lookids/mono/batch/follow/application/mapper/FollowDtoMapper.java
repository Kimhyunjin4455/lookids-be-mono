package lookids.batch.follow.application.mapper;

import org.springframework.stereotype.Component;

import lookids.batch.follow.adaptor.in.kafka.event.FollowEvent;
import lookids.batch.follow.application.port.dto.FollowCountResponseDto;
import lookids.batch.follow.application.port.dto.FollowCountSaveDto;
import lookids.batch.follow.application.port.dto.FollowEventDto;
import lookids.batch.follow.application.port.dto.FollowLogSaveDto;
import lookids.batch.follow.domain.model.FollowCount;
import lookids.batch.follow.domain.model.FollowLog;

@Component
public class FollowDtoMapper {
	public FollowEventDto toFollowEventDto(FollowEvent followEvent) {
		return FollowEventDto.builder()
			.senderUuid(followEvent.getSenderUuid())
			.receiverUuid(followEvent.getReceiverUuid())
			.createdAt(followEvent.getCreatedAt())
			.build();
	}

	public FollowLogSaveDto toFollowLogSaveDto(FollowLog followLog) {
		return FollowLogSaveDto.builder()
			.id(followLog.getId())
			.senderUuid(followLog.getSenderUuid())
			.receiverUuid(followLog.getReceiverUuid())
			.logType(followLog.getLogType())
			.createdAt(followLog.getCreatedAt())
			.processed(followLog.getProcessed())
			.build();
	}

	public FollowLogSaveDto toFollowLogUpdateDto(FollowLog followLog) {
		return FollowLogSaveDto.builder()
			.id(followLog.getId())
			.senderUuid(followLog.getSenderUuid())
			.receiverUuid(followLog.getReceiverUuid())
			.logType(followLog.getLogType())
			.createdAt(followLog.getCreatedAt())
			.processed(true)
			.build();
	}

	public FollowCountSaveDto toFollowCountSaveDto(FollowCount followCount) {
		return FollowCountSaveDto.builder()
			.id(followCount.getId())
			.uuid(followCount.getUuid())
			.followerCount(followCount.getFollowerCount())
			.followingCount(followCount.getFollowingCount())
			.updateAt(followCount.getUpdateAt())
			.build();
	}

	public FollowCountResponseDto toFollowCountResponseDto(FollowCount followCount) {
		return FollowCountResponseDto.builder()
			.followingCount(followCount.getFollowingCount())
			.followerCount(followCount.getFollowerCount())
			.build();
	}
}
