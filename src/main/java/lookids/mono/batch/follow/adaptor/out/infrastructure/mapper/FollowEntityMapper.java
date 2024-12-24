package lookids.mono.batch.follow.adaptor.out.infrastructure.mapper;

import org.springframework.stereotype.Component;

import lookids.mono.batch.follow.adaptor.out.infrastructure.entity.FollowCountEntity;
import lookids.mono.batch.follow.adaptor.out.infrastructure.entity.FollowLogEntity;
import lookids.mono.batch.follow.application.port.dto.FollowCountSaveDto;
import lookids.mono.batch.follow.application.port.dto.FollowLogSaveDto;
import lookids.mono.batch.follow.domain.model.FollowCount;
import lookids.mono.batch.follow.domain.model.FollowLog;

@Component
public class FollowEntityMapper {

	public FollowLogEntity toFollowLogEntity(FollowLogSaveDto followLogSaveDto) {
		return FollowLogEntity.builder()
			.id(followLogSaveDto.getId())
			.senderUuid(followLogSaveDto.getSenderUuid())
			.receiverUuid(followLogSaveDto.getReceiverUuid())
			.logType(followLogSaveDto.getLogType())
			.createdAt(followLogSaveDto.getCreatedAt())
			.processed(followLogSaveDto.getProcessed())
			.build();
	}

	public FollowCount toFollowCount(FollowCountEntity followCountEntity) {
		return FollowCount.builder()
			.id(followCountEntity.getId())
			.uuid(followCountEntity.getUuid())
			.followingCount(followCountEntity.getFollowingCount())
			.followerCount(followCountEntity.getFollowerCount())
			.updateAt(followCountEntity.getUpdateAt())
			.build();
	}

	public FollowCount toNullFollowCount(String uuid) {
		return FollowCount.builder().uuid(uuid).followingCount(0).followerCount(0).build();
	}

	public FollowLog toFollowLog(FollowLogEntity followLogEntity) {
		return FollowLog.builder()
			.id(followLogEntity.getId())
			.senderUuid(followLogEntity.getSenderUuid())
			.receiverUuid(followLogEntity.getReceiverUuid())
			.logType(followLogEntity.getLogType())
			.createdAt(followLogEntity.getCreatedAt())
			.processed(followLogEntity.getProcessed())
			.build();
	}

	public FollowCountEntity toFollowCountEntity(FollowCountSaveDto followCountSaveDto) {
		return FollowCountEntity.builder()
			.id(followCountSaveDto.getId())
			.uuid(followCountSaveDto.getUuid())
			.followingCount(followCountSaveDto.getFollowingCount())
			.followerCount(followCountSaveDto.getFollowerCount())
			.updateAt(followCountSaveDto.getUpdateAt())
			.build();
	}
}
