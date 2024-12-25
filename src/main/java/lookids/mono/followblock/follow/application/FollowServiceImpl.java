package lookids.mono.followblock.follow.application;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.followblock.follow.domain.Follow;
import lookids.mono.followblock.follow.domain.FollowInfo;
import lookids.mono.followblock.follow.dto.in.FollowRequestDto;
import lookids.mono.followblock.follow.dto.in.KafkaFollowDto;
import lookids.mono.followblock.follow.dto.in.KafkaFollowRequestDto;
import lookids.mono.followblock.follow.dto.in.KafkaUserUpdateRequestDto;
import lookids.mono.followblock.follow.dto.out.FollowInfoResponseDto;
import lookids.mono.followblock.follow.dto.out.FollowResponseDto;
import lookids.mono.followblock.follow.dto.out.KafkaAlarmFollowResponseDto;
import lookids.mono.followblock.follow.dto.out.KafkaFollowResponseDto;
import lookids.mono.followblock.follow.dto.out.KafkaFollowerResponseDto;
import lookids.mono.followblock.follow.infrastructure.FollowInfoRepository;
import lookids.mono.followblock.follow.infrastructure.FollowRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

	private final FollowRepository followRepository;
	private final FollowInfoRepository followInfoRepository;
	private final KafkaTemplate<String, KafkaAlarmFollowResponseDto> alarmKafkaTemplate;
	private final KafkaTemplate<String, KafkaFollowResponseDto> feedKafkaTemplate;
	private final KafkaTemplate<String, KafkaFollowerResponseDto> followerKafkaTemplate;

	@Override
	@Transactional
	public Boolean createFollow(FollowRequestDto followRequestDto) {
		Optional<Follow> existFollow = followRepository.findByFollowerUuidAndFollowingUuid(
			followRequestDto.getFollowerUuid(), followRequestDto.getFollowingUuid());

		if (!existFollow.isPresent()) {
			KafkaAlarmFollowResponseDto kafkaAlarmFollowResponseDto = KafkaAlarmFollowResponseDto.builder()
				.receiverUuid(followRequestDto.getFollowerUuid())
				.senderUuid(followRequestDto.getFollowingUuid())
				.createdAt(LocalDateTime.now())
				.build();

			followRepository.save(followRequestDto.toEntity());
			alarmKafkaTemplate.send("follow-create", kafkaAlarmFollowResponseDto);
			return true;

		} else {
			KafkaAlarmFollowResponseDto kafkaAlarmFollowResponseDto = KafkaAlarmFollowResponseDto.builder()
				.receiverUuid(followRequestDto.getFollowerUuid())
				.senderUuid(followRequestDto.getFollowingUuid())
				.build();

			followRepository.delete(existFollow.get());
			followInfoRepository.deleteBySenderUuidAndReceiverUuid(followRequestDto.getFollowingUuid(),
				followRequestDto.getFollowerUuid());
			alarmKafkaTemplate.send("follow-delete", kafkaAlarmFollowResponseDto);
			return false;
		}
	}

	@Override
	public Boolean readFollow(String followerUuid, String followingUuid) {
		Boolean chk = followRepository.existsByFollowerUuidAndFollowingUuid(followerUuid, followingUuid);
		return chk;
	}

	@Override
	public Page<FollowResponseDto> readFollowerList(String uuid, Pageable pageable) {
		return followRepository.findByFollowerUuid(uuid, pageable);
	}

	@Override
	public Page<FollowResponseDto> readFollowingList(String uuid, Pageable pageable) {
		return followRepository.findByFollowingUuid(uuid, pageable);
	}

	@Override
	public Page<FollowInfoResponseDto> readFollowerUserProfile(String userUuid, Pageable pageable) {
		return followInfoRepository.findByReceiverUuid(userUuid, pageable);
	}

	@Override
	public Page<FollowInfoResponseDto> readFollowingUserProfile(String userUuid, Pageable pageable) {
		return followInfoRepository.findBySenderUuid(userUuid, pageable);
	}

	@KafkaListener(topics = "follow-request", groupId = "feed-group", containerFactory = "FeedFollowListenerContainerFactory")
	public void consumeForFollowUuid(KafkaFollowRequestDto kafkaFollowRequestDto) {

		String uuid = kafkaFollowRequestDto.getUuid();
		List<Follow> followList = followRepository.findByFollowingUuid(uuid);

		feedKafkaTemplate.send("follow-response", KafkaFollowResponseDto.toDto(uuid, followList));

	}

	@KafkaListener(topics = "follower-request", groupId = "feed-group", containerFactory = "FollowerListenerContainerFactory")
	public void consumeForFollowerUuid(KafkaFollowRequestDto kafkaFollowRequestDto) {

		String uuid = kafkaFollowRequestDto.getUuid();
		List<Follow> followerList = followRepository.findByFollowerUuid(uuid);

		followerKafkaTemplate.send("follower-response", KafkaFollowerResponseDto.toDto(uuid, followerList));

	}

	@KafkaListener(topics = "userprofile-response", groupId = "feed-group", containerFactory = "FollowInfoContainerFactory")
	public void consumeFollowInfo(KafkaFollowDto kafkaFollowDto) {

		followInfoRepository.save(kafkaFollowDto.toEntity());

	}

	@KafkaListener(topics = "userprofile-nickname-update", groupId = "es-group", containerFactory = "UserUpdateContainerFactory")
	public void consumeUserNicknameUpdate(KafkaUserUpdateRequestDto kafkaUserUpdateRequestDto) {

		List<FollowInfo> senderList = followInfoRepository.findBySenderUuid(kafkaUserUpdateRequestDto.getUuid());
		List<FollowInfo> receiverList = followInfoRepository.findByReceiverUuid(kafkaUserUpdateRequestDto.getUuid());

		for (FollowInfo sender : senderList) {
			FollowInfo followInfo = FollowInfo.builder()
				.id(sender.getId())
				.senderUuid(sender.getSenderUuid())
				.senderNickname(kafkaUserUpdateRequestDto.getNickname())
				.senderTag(kafkaUserUpdateRequestDto.getTag())
				.senderImage(sender.getSenderImage())
				.receiverUuid(sender.getReceiverUuid())
				.receiverNickname(sender.getReceiverNickname())
				.receiverTag(sender.getReceiverTag())
				.receiverImage(sender.getReceiverImage())
				.build();
			followInfoRepository.save(followInfo);
		}

		for (FollowInfo receiver : receiverList) {
			FollowInfo followInfo = FollowInfo.builder()
				.id(receiver.getId())
				.senderUuid(receiver.getSenderUuid())
				.senderNickname(receiver.getSenderNickname())
				.senderTag(receiver.getSenderTag())
				.senderImage(receiver.getSenderImage())
				.receiverUuid(receiver.getReceiverUuid())
				.receiverNickname(kafkaUserUpdateRequestDto.getNickname())
				.receiverTag(kafkaUserUpdateRequestDto.getTag())
				.receiverImage(receiver.getReceiverImage())
				.build();
			followInfoRepository.save(followInfo);
		}

	}

	@KafkaListener(topics = "userprofile-image-update", groupId = "es-group", containerFactory = "UserUpdateContainerFactory")
	public void consumeUserImageUpdate(KafkaUserUpdateRequestDto kafkaUserUpdateRequestDto) {

		List<FollowInfo> sender = followInfoRepository.findBySenderUuid(kafkaUserUpdateRequestDto.getUuid());
		List<FollowInfo> receiver = followInfoRepository.findByReceiverUuid(kafkaUserUpdateRequestDto.getUuid());

		for (FollowInfo send : sender) {
			FollowInfo followInfo = FollowInfo.builder()
				.id(send.getId())
				.senderUuid(send.getSenderUuid())
				.senderNickname(send.getSenderNickname())
				.senderTag(send.getSenderTag())
				.senderImage(kafkaUserUpdateRequestDto.getImage())
				.receiverUuid(send.getReceiverUuid())
				.receiverNickname(send.getReceiverNickname())
				.receiverTag(send.getReceiverTag())
				.receiverImage(send.getReceiverImage())
				.build();
			followInfoRepository.save(followInfo);

		}

		for (FollowInfo receive : receiver) {
			FollowInfo followInfo = FollowInfo.builder()
				.id(receive.getId())
				.senderUuid(receive.getSenderUuid())
				.senderNickname(receive.getSenderNickname())
				.senderTag(receive.getSenderTag())
				.senderImage(receive.getSenderImage())
				.receiverUuid(receive.getReceiverUuid())
				.receiverNickname(receive.getReceiverNickname())
				.receiverTag(receive.getReceiverTag())
				.receiverImage(kafkaUserUpdateRequestDto.getImage())
				.build();
			followInfoRepository.save(followInfo);

		}
	}

}