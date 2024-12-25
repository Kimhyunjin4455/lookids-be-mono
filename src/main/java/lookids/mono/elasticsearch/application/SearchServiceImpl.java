package lookids.mono.elasticsearch.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.elasticsearch.domain.SearchFeed;
import lookids.mono.elasticsearch.domain.SearchPet;
import lookids.mono.elasticsearch.domain.SearchUser;
import lookids.mono.elasticsearch.dto.in.KafkaFeedCreateRequestDto;
import lookids.mono.elasticsearch.dto.in.KafkaFeedDeleteRequestDto;
import lookids.mono.elasticsearch.dto.in.KafkaPetCreateRequestDto;
import lookids.mono.elasticsearch.dto.in.KafkaPetDeleteRequestDto;
import lookids.mono.elasticsearch.dto.in.KafkaPetUpdateRequestDto;
import lookids.mono.elasticsearch.dto.in.KafkaUserCreateRequestDto;
import lookids.mono.elasticsearch.dto.in.KafkaUserDeleteRequestDto;
import lookids.mono.elasticsearch.dto.in.KafkaUserImageUpdateRequestDto;
import lookids.mono.elasticsearch.dto.in.KafkaUserNicknameUpdateRequestDto;
import lookids.mono.elasticsearch.dto.out.SearchFeedResponseDto;
import lookids.mono.elasticsearch.dto.out.SearchPetResponseDto;
import lookids.mono.elasticsearch.dto.out.SearchUserResponseDto;
import lookids.mono.elasticsearch.infrastructure.SearchFeedRepository;
import lookids.mono.elasticsearch.infrastructure.SearchPetRepository;
import lookids.mono.elasticsearch.infrastructure.SearchUserRepository;

@Slf4j
@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {

	private final KafkaBatchCollector kafkaBatchCollector;
	private final SearchUserRepository searchUserRepository;
	private final SearchFeedRepository searchFeedRepository;
	private final SearchPetRepository searchPetRepository;

	@Override
	public Page<SearchUserResponseDto> searchUser(String searchUser, Pageable pageable) {

		String[] parts = searchUser.split("@");

		String nickname = parts[0];
		String tag = parts.length > 1 ? parts[1] : null;

		Page<SearchUser> userList;

		if (tag == null) {
			userList = searchUserRepository.findByNickname(nickname, pageable);
		} else {
			userList = searchUserRepository.findByNicknameAndTag(nickname, tag, pageable);
		}

		return userList.map(SearchUserResponseDto::toDto);
	}

	@Override
	public Page<SearchFeedResponseDto> searchFeedByTag(String searchFeed, Pageable pageable) {
		Page<SearchFeed> searchFeedList = searchFeedRepository.findByTagListContaining(searchFeed, pageable);
		return searchFeedList.map(SearchFeedResponseDto::toDto);
	}

	@Override
	public Page<SearchFeedResponseDto> searchFeedByPetCode(String searchFeed, Pageable pageable) {
		Page<SearchFeed> searchFeedList = searchFeedRepository.findByPetCode(searchFeed, pageable);
		return searchFeedList.map(SearchFeedResponseDto::toDto);
	}

	@Override
	public Page<SearchPetResponseDto> searchPet(String searchPet, Pageable pageable) {
		Page<SearchPet> searchPetList = searchPetRepository.findByPetName(searchPet, pageable);
		return searchPetList.map(SearchPetResponseDto::toDto);
	}

	@KafkaListener(topics = "userprofile-create", groupId = "usercreate-group", containerFactory = "UserCreateContainerFactory")
	public void consumeUserCreate(KafkaUserCreateRequestDto kafkaUserCreateRequestDto) {

		SearchUser searchUser = SearchUser.builder()
			.uuid(kafkaUserCreateRequestDto.getUuid())
			.nickname(kafkaUserCreateRequestDto.getNickname())
			.tag(kafkaUserCreateRequestDto.getTag())
			.image(kafkaUserCreateRequestDto.getImage())
			.state(true)
			.build();

		kafkaBatchCollector.addUserCreateBatch(searchUser);

	}

	@KafkaListener(topics = "userprofile-nickname-update", groupId = "usernicknameupdate-group", containerFactory = "UserNicknameUpdateContainerFactory")
	public void consumeUserNicknameUpdate(KafkaUserNicknameUpdateRequestDto kafkaUserNicknameUpdateRequestDto) {

		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		SearchUser searchUser = searchUserRepository.findByUuid(kafkaUserNicknameUpdateRequestDto.getUuid());

		kafkaBatchCollector.addUserUpdateBatch(
			KafkaUserNicknameUpdateRequestDto.toUpdateNickname(searchUser, kafkaUserNicknameUpdateRequestDto));

	}

	@KafkaListener(topics = "userprofile-image-update", groupId = "userimageupdate-group", containerFactory = "UserImageUpdateContainerFactory")
	public void consumeUserImageUpdate(KafkaUserImageUpdateRequestDto kafkaUserImageUpdateRequestDto) {

		SearchUser searchUser = searchUserRepository.findByUuid(kafkaUserImageUpdateRequestDto.getUuid());

		kafkaBatchCollector.addUserUpdateBatch(
			KafkaUserImageUpdateRequestDto.toUpdateImage(searchUser, kafkaUserImageUpdateRequestDto));

	}

	@KafkaListener(topics = "account-delete", groupId = "userdelete-group", containerFactory = "UserDeleteContainerFactory")
	public void consumeUserDelete(KafkaUserDeleteRequestDto kafkaUserDeleteRequestDto) {

		SearchUser searchUser = searchUserRepository.findByUuid(kafkaUserDeleteRequestDto.getUuid());

		SearchUser deleteUser = searchUser.builder()
			.id(searchUser.getId())
			.uuid(searchUser.getUuid())
			.tag(searchUser.getTag())
			.nickname(searchUser.getNickname())
			.image(searchUser.getImage())
			.state(false)
			.build();

		kafkaBatchCollector.addUserDeleteBatch(deleteUser);

	}

	@KafkaListener(topics = "feed-create", groupId = "feedcreate-group", containerFactory = "FeedCreateContainerFactory")
	public void consumeFeedCreate(KafkaFeedCreateRequestDto kafkaFeedCreateRequestDto) {

		SearchFeed searchFeed = SearchFeed.builder()
			.feedCode(kafkaFeedCreateRequestDto.getFeedCode())
			.petCode(kafkaFeedCreateRequestDto.getPetCode())
			.tagList(kafkaFeedCreateRequestDto.getTagList())
			.mediaUrlList(kafkaFeedCreateRequestDto.getMediaUrlList())
			.build();

		kafkaBatchCollector.addFeedCreateBatch(searchFeed);

	}

	@KafkaListener(topics = "feed-delete", groupId = "feeddelete-group", containerFactory = "FeedDeleteContainerFactory")
	public void consumeFeedDelete(KafkaFeedDeleteRequestDto kafkaFeedDeleteRequestDto) {

		kafkaBatchCollector.addFeedDeleteBatch(kafkaFeedDeleteRequestDto.getFeedCode());

	}

	@KafkaListener(topics = "petprofile-create", groupId = "petcreate-group", containerFactory = "PetCreateContainerFactory")
	public void consumePetCreate(KafkaPetCreateRequestDto kafkaPetCreateRequestDto) {

		SearchPet searchPet = SearchPet.builder()
			.petName(kafkaPetCreateRequestDto.getPetName())
			.petType(kafkaPetCreateRequestDto.getPetType())
			.petImage(kafkaPetCreateRequestDto.getPetImage())
			.petCode(kafkaPetCreateRequestDto.getPetCode())
			.userNickname(kafkaPetCreateRequestDto.getUserNickname())
			.userTag(kafkaPetCreateRequestDto.getUserTag())
			.build();

		kafkaBatchCollector.addPetCreateBatch(searchPet);

	}

	@KafkaListener(topics = "petprofile-update", groupId = "petupdate-group", containerFactory = "PetUpdateContainerFactory")
	public void consumePetUpdate(KafkaPetUpdateRequestDto kafkaPetUpdateRequestDto) {

		SearchPet searchPet = searchPetRepository.findByPetCode(kafkaPetUpdateRequestDto.getPetCode());

		kafkaBatchCollector.addPetUpdateBatch(
			KafkaPetUpdateRequestDto.toUpdate(searchPet, kafkaPetUpdateRequestDto).toEntity());

	}

	@KafkaListener(topics = "petprofile-delete", groupId = "petdelete-group", containerFactory = "PetDeleteContainerFactory")
	public void consumePetDelete(KafkaPetDeleteRequestDto kafkaPetDeleteRequestDto) {

		kafkaBatchCollector.addPetDeleteBatch(kafkaPetDeleteRequestDto.getPetCode());

	}

}
