package lookids.mono.chatting.application;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.chatting.domain.ChatRoom;
import lookids.mono.chatting.dto.in.UserKafkaRequestDto;
import lookids.mono.chatting.infrastructure.ChatRoomRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserKafkaListener {
	private final ChattingService chattingService;
	private final ChatRoomRepository chatRoomRepository;

	@KafkaListener(topics = "account-delete", groupId = "user-chat", containerFactory = "userEventListenerContainerFactory")
	public void consumeFeed(UserKafkaRequestDto requestDto) {
		String uuid = requestDto.getUuid();

		List<ChatRoom> chatRooms = chatRoomRepository.findByParticipantUserId(uuid, Pageable.unpaged()).getContent();

		for (ChatRoom chatRoom : chatRooms) {
			chatRoomRepository.deleteById(chatRoom.getId());
		}
	}
}
