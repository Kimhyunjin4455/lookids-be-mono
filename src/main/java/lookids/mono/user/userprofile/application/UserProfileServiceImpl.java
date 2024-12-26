package lookids.mono.user.userprofile.application;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.common.entity.BaseResponseStatus;
import lookids.mono.common.exception.BaseException;
import lookids.mono.user.userprofile.domain.UserProfile;
import lookids.mono.user.userprofile.dto.in.UserProfileImgDto;
import lookids.mono.user.userprofile.dto.in.UserProfileNicknameDto;
import lookids.mono.user.userprofile.dto.in.UserProfileRequestDto;
import lookids.mono.user.userprofile.dto.in.UserProfileTierDto;
import lookids.mono.user.userprofile.dto.in.UserProfileUpdateDto;
import lookids.mono.user.userprofile.dto.out.FollowKafkaDto;
import lookids.mono.user.userprofile.dto.out.UserProfileKafkaDto;
import lookids.mono.user.userprofile.dto.out.UserProfileResponseDto;
import lookids.mono.user.userprofile.infrastructure.UserProfileRepository;
import lookids.mono.user.userprofile.vo.in.CommentEventVo;
import lookids.mono.user.userprofile.vo.in.FeedEventVo;
import lookids.mono.user.userprofile.vo.in.FollowEventVo;
import lookids.mono.user.userprofile.vo.in.ReplyEventVo;
import lookids.mono.user.userprofile.vo.out.FollowKafkaVo;
import lookids.mono.user.userprofile.vo.out.NicknameKafkaVo;
import lookids.mono.user.userprofile.vo.out.ProfileImageKafkaVo;
import lookids.mono.user.userprofile.vo.out.UserProfileKafkaVo;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProfileServiceImpl implements UserProfileService {

	private final UserProfileRepository userProfileRepository;

	@Value("${profile.crate}")
	private String profileCreateTopic;

	@Value("${profile.image.update}")
	private String imageUpdateTopic;

	@Value("${profile.nickname.update}")
	private String nicknameUpdateTopic;

	@Value("${profile.delete}")
	private String profileDeleteTopic;

	@Override
	public void createUserProfile(UserProfileRequestDto userProfileRequestDto) {

		UserProfile userProfile = userProfileRepository.save(
			userProfileRequestDto.toEntity(generateUniqueTag(userProfileRequestDto.getNickname()),
				generateRandomImage()));
		sendMessage(profileCreateTopic, UserProfileKafkaDto.toDto(userProfile).toVo());
	}

	@Override
	public void createUserProfileService(String uuid, String nickname) {
		UserProfileRequestDto userProfileRequestDto = UserProfileRequestDto.toDtoString(uuid, nickname);
		UserProfile userProfile = userProfileRepository.save(
			userProfileRequestDto.toEntity(generateUniqueTag(userProfileRequestDto.getNickname()),
				generateRandomImage()));
		sendMessage(profileCreateTopic, UserProfileKafkaDto.toDto(userProfile).toVo());
	}

	@Override
	public void updateUserProfile(UserProfileUpdateDto userProfileUpdateDto) {
		UserProfile userProfile = userProfileRepository.findByUserUuid(userProfileUpdateDto.getUserUuid())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		userProfileRepository.save(userProfileUpdateDto.toUpdate(userProfile));
	}

	private final KafkaTemplate<String, ProfileImageKafkaVo> imageKafkaTemplate;

	@Override
	public void updateUserProfileImage(UserProfileImgDto userProfileImgDto) {
		UserProfile userProfile = userProfileRepository.findByUserUuid(userProfileImgDto.getUserUuid())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		UserProfile newProfile = userProfileRepository.save(userProfileImgDto.toEntity(userProfile));
		imageKafkaTemplate.send(imageUpdateTopic, UserProfileKafkaDto.toDto(newProfile).toImageVo());
	}

	@Override
	public void updateUserProfileTier(UserProfileTierDto userProfileTierDto) {
		UserProfile userProfile = userProfileRepository.findByUserUuid(userProfileTierDto.getUserUuid())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		userProfileRepository.save(userProfileTierDto.toEntity(userProfile));
	}

	@Override
	public void deleteUserProfile(String userUuid) {
		UserProfile userProfile = userProfileRepository.findByUserUuid(userUuid)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		userProfileRepository.deleteById(userProfile.getId());
		sendMessage(profileDeleteTopic, UserProfileKafkaDto.toDto(userProfile).toVo());
	}

	private final KafkaTemplate<String, NicknameKafkaVo> nicknameKafkaTemplate;

	@Override
	public void updateUserProfileNickname(UserProfileNicknameDto userProfileNicknameDto) {
		UserProfile userProfile = userProfileRepository.findByUserUuid(userProfileNicknameDto.getUserUuid())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		UserProfile newProfile = userProfileRepository.save(
			userProfileNicknameDto.toEntity(userProfile, generateUniqueTag(userProfileNicknameDto.getNickname())));
		nicknameKafkaTemplate.send(nicknameUpdateTopic, UserProfileKafkaDto.toDto(newProfile).toNicknameVo());
	}

	@Override
	public UserProfileResponseDto readUserProfile(String userUuid) {
		return UserProfileResponseDto.toDto(userProfileRepository.findByUserUuid(userUuid)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA)));
	}

	@Override
	public UserProfileResponseDto readUserProfileWithTag(String nickname, String tag) {
		return UserProfileResponseDto.toDto(userProfileRepository.findByNicknameAndTag(nickname, tag)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA)));
	}

	private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int TAG_LENGTH = 5;
	private static final String[] STRINGS = {"media/default_profile_1.jpeg", "media/default_profile_2.jpeg",
		"media/default_profile_3.jpeg", "media/default_profile_4.jpeg", "media/default_profile_5.jpeg"};
	private static final Random RANDOM = new Random();

	public String generateRandomTag() {
		return RANDOM.ints(TAG_LENGTH, 0, CHAR_POOL.length())
			.mapToObj(CHAR_POOL::charAt)
			.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
			.toString();
	}

	public String generateRandomImage() {
		return STRINGS[RANDOM.nextInt(STRINGS.length)];
	}

	private String generateUniqueTag(String nickname) {
		int maxAttempts = 5;  // 최대 시도 횟수
		int attempt = 0;
		String tag;

		do {
			tag = generateRandomTag();
			attempt++;
			if (attempt >= maxAttempts) {
				throw new BaseException(BaseResponseStatus.DUPLICATED_TAG);
			}
		} while (userProfileRepository.existsByNicknameAndTag(nickname, tag));

		return tag;
	}

	private final KafkaTemplate<String, UserProfileKafkaVo> userProfileKafkaTemplate;

	@Value("${comment.join}")
	private String commentJoinTopic;

	@Value("${reply.join}")
	private String replyJoinTopic;

	@Value("${feed.join}")
	private String feedJoinTopic;

	@KafkaListener(topics = "${comment.create}", groupId = "${group-id.user}", containerFactory = "commentUserListenerContainerFactory")
	public void consumeCommentEvent(CommentEventVo commentEventVo) {

		log.info("consumeCommentEvent: {}", commentEventVo);

		UserProfile userProfile = userProfileRepository.findByUserUuid(commentEventVo.getUuid())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		sendMessage(commentJoinTopic, UserProfileKafkaDto.toDto(userProfile).toVo());
	}

	@KafkaListener(topics = "${reply.create}", groupId = "${group-id.user}", containerFactory = "replyUserListenerContainerFactory")
	public void consumeReplyEvent(ReplyEventVo replyEventVo) {

		log.info("consumeReplyEvent: {}", replyEventVo);

		UserProfile userProfile = userProfileRepository.findByUserUuid(replyEventVo.getUuid())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));

		sendMessage(replyJoinTopic, UserProfileKafkaDto.toDto(userProfile).toVo());
	}

	@KafkaListener(topics = "${feed.create}", groupId = "${group-id.user}", containerFactory = "feedUserListenerContainerFactory")
	public void consumeFeedEvent(FeedEventVo feedEventVo) {

		log.info("consumeFeedEvent: {}", feedEventVo);

		UserProfile userProfile = userProfileRepository.findByUserUuid(feedEventVo.getUuid())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));

		sendMessage(feedJoinTopic, UserProfileKafkaDto.toDto(userProfile).toVo());
	}

	private final KafkaTemplate<String, FollowKafkaVo> followJoinKafkaTemplate;
	@Value("${follow.join}")
	private String followJoinTopic;

	@KafkaListener(topics = "${follow.create}", groupId = "${group-id.user}", containerFactory = "followUserListenerContainerFactory")
	public void consumeFollowEvent(FollowEventVo feedEventVo) {

		log.info("consumeFollowEvent: {}", feedEventVo);

		UserProfile senderProfile = userProfileRepository.findByUserUuid(feedEventVo.getSenderUuid())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		UserProfile receiverProfile = userProfileRepository.findByUserUuid(feedEventVo.getReceiverUuid())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));

		followJoinKafkaTemplate.send(followJoinTopic, FollowKafkaDto.toDto(senderProfile, receiverProfile).toVo());
	}

	public void sendMessage(String topic, UserProfileKafkaVo userProfileKafkaVo) {
		userProfileKafkaTemplate.send(topic, userProfileKafkaVo);
	}
}
