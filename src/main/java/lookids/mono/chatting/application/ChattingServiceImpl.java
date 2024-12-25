package lookids.mono.chatting.application;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ChangeStreamEvent;
import org.springframework.data.mongodb.core.ChangeStreamOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.client.model.changestream.OperationType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.chatting.domain.ChatMessage;
import lookids.mono.chatting.domain.ChatRoom;
import lookids.mono.chatting.domain.Participant;
import lookids.mono.chatting.dto.in.ChatRoomNameUpdateRequestDto;
import lookids.mono.chatting.dto.in.ChatRoomRequestDto;
import lookids.mono.chatting.dto.in.ChatRoomUpdateRequestDto;
import lookids.mono.chatting.dto.in.ChattingRequestDto;
import lookids.mono.chatting.dto.in.ChattingUpdateRequestDto;
import lookids.mono.chatting.dto.out.ChatRoomResponseDto;
import lookids.mono.chatting.dto.out.ChattingResponseDto;
import lookids.mono.chatting.dto.out.CheckOneToOneChatResponseDto;
import lookids.mono.chatting.dto.out.LastReadChatMessageResponseDto;
import lookids.mono.chatting.dto.out.NotificationKafkaRequestDto;
import lookids.mono.chatting.dto.out.ParticipantListDto;
import lookids.mono.chatting.dto.out.RoomIdResponseDto;
import lookids.mono.chatting.infrastructure.ChatMessageRepository;
import lookids.mono.chatting.infrastructure.ChatMessageRepositoryCustom;
import lookids.mono.chatting.infrastructure.ChatRoomRepository;
import lookids.mono.chatting.infrastructure.ChatRoomRepositoryCustom;
import lookids.mono.common.entity.BaseResponseStatus;
import lookids.mono.common.exception.BaseException;
import lookids.mono.common.utils.CursorPage;
import reactor.core.publisher.Flux;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChattingServiceImpl implements ChattingService {
	private final ChatRoomRepository chatRoomRepository;
	private final ChatMessageRepositoryCustom chatMessageRepositoryCustom;
	private final ChatRoomRepositoryCustom chatRoomRepositoryCustom;
	private final ChatMessageRepository chatMessageRepository;
	private final ReactiveMongoTemplate reactiveMongoTemplate;
	private final KafkaTemplate<String, NotificationKafkaRequestDto> kafkaTemplate;

	@Override
	public RoomIdResponseDto createChatRoom(ChatRoomRequestDto chatRoomRequestDto) { // 채팅방 생성
		ChatRoom chatroom = chatRoomRepository.save(chatRoomRequestDto.toEntity());
		return RoomIdResponseDto.toDto(chatroom.getId());
	}

	@Override
	public void createChatMessage(ChattingRequestDto chattingRequestDto) {
		ChatMessage savedChatMessage = chatMessageRepository.save(chattingRequestDto.toEntity());

		// ChatRoom 조회 및 처리
		chatRoomRepository.findById(savedChatMessage.getRoomId()).ifPresent(chatRoom -> {
			// Kafka 알림을 보낼 사용자 목록 (isOnline이 false인 경우)
			List<String> offlineReceiverUuids = chatRoom.getParticipants()
				.stream()
				.filter(participant -> !participant.getIsOnline()) // 온라인 상태가 아닌 사용자만 필터링
				.map(Participant::getUserId)
				.filter(userId -> !userId.equals(savedChatMessage.getSenderId())) // 메시지 보낸 사람 제외
				.toList();

			chatRoom.getParticipants().forEach(participant -> {
				if (participant.getIsOnline()) {
					// 온라인 상태이면 마지막 읽은 메시지 업데이트
					participant.updateLastReadChatMessage(savedChatMessage.getId());
				} else {
					// 오프라인 상태이면 안 읽은 메시지 수 증가
					participant.incrementUnreadCount();
				}
			});

			// ChatRoom 엔티티 업데이트
			chatRoomRepository.save(ChatRoomUpdateRequestDto.toUpdateEntity(chatRoom, savedChatMessage.getMessage()));

			// Kafka 알림: 오프라인 사용자에게만 전송
			if (!offlineReceiverUuids.isEmpty()) {
				kafkaTemplate.send("chatting-create",
					NotificationKafkaRequestDto.toDto(savedChatMessage, offlineReceiverUuids));
			}
		});
	}

	@Override // 일반 페이징 처리. 유저 id에 맞는 방 리스트 가져오기
	public Page<ChatRoomResponseDto> readChatRoomsByUserId(String userId, Pageable pageable) {
		return chatRoomRepository.findByParticipantUserId(userId, pageable)
			.map(chatRoom -> ChatRoomResponseDto.toDto(chatRoom, userId));
	}

	@Override // 커서 페이징 처리. 유저 id에 맞는 방 리스트 가져오기
	public CursorPage<ChatRoomResponseDto> readChatRoomsByUserIdCursorPage(String userId, String lastId, int size,
		int page) {
		CursorPage<ChatRoom> chatRoomCursorPage = chatRoomRepositoryCustom.getChatRoom(userId, lastId, size, page);
		List<ChatRoomResponseDto> dtoList = chatRoomCursorPage.getContent()
			.stream()
			.map(chatRoom -> ChatRoomResponseDto.toDto(chatRoom, userId)) // userId를 함께 전달
			.toList();

		return new CursorPage<>(dtoList, chatRoomCursorPage.getNextCursor(), chatRoomCursorPage.hasNext(),
			chatRoomCursorPage.getPageSize(), chatRoomCursorPage.getPage());
	}

	@Override // db의 변화를 감지해서 api 실행 후에 생긴 새로운 채팅방들만 출력하는 api
	public Flux<ChatRoomResponseDto> readReactiveChatRoomsByUserId(String userId) {
		ChangeStreamOptions options = ChangeStreamOptions.builder()
			.filter(Aggregation.newAggregation(Aggregation.match(
				Criteria.where("operationType").in(OperationType.REPLACE.getValue(), OperationType.INSERT.getValue()))))
			.build();

		return reactiveMongoTemplate.changeStream("chat_room", options, Document.class)
			.map(ChangeStreamEvent::getBody)
			.map(document -> ChatRoomResponseDto.fromDocument(document, userId)); // userId 전달
	}

	@Override // 일반 페이징 처리. 특정 채팅방의 채팅내용을 가져오는 api
	public Page<ChattingResponseDto> readChatMessageByRoomIdPage(String roomId, Pageable pageable) {
		return chatMessageRepository.findByRoomId(roomId, pageable).map(ChattingResponseDto::toDto);
	}

	@Override // 커서 페이징 처리. 특정 채팅방의 채팅 내용을 가져오는 api
	public CursorPage<ChattingResponseDto> readChatMessageByRoomIdCursorPage(String roomId, String lastId, int size,
		int page) {
		CursorPage<ChatMessage> chatMessageCursorPage = chatMessageRepositoryCustom.getChatMessage(roomId, lastId, size,
			page);
		List<ChattingResponseDto> dtoList = chatMessageCursorPage.getContent()
			.stream()
			.map(ChattingResponseDto::toDto) // ChatMessage -> ChattingResponseDto 변환
			.toList();

		return new CursorPage<>(dtoList, chatMessageCursorPage.getNextCursor(), chatMessageCursorPage.hasNext(),
			chatMessageCursorPage.getPageSize(), chatMessageCursorPage.getPage());
	}

	@Override // db의 변화를 감지해서 api 실행 후에 생긴 새로운 메세지들만 출력하는 api
	public Flux<ChattingResponseDto> readReactiveChatMessageByRoomId(String roomId) {
		ChangeStreamOptions options = ChangeStreamOptions.builder()
			.filter(Aggregation.newAggregation(Aggregation.match(
					Criteria.where("operationType").in(OperationType.INSERT.getValue(), OperationType.REPLACE.getValue())),
				Aggregation.match(Criteria.where("fullDocument.roomId").is(roomId))))
			.build();

		return reactiveMongoTemplate.changeStream("chat_message", options, Document.class)
			.map(ChangeStreamEvent::getBody)
			.map(ChattingResponseDto::toDtoFromDocument);
	}

	@Override // 채팅 메세지 수정용 api
	public void updateChatMessage(ChattingUpdateRequestDto chattingUpdateRequestDto) {
		ChatMessage chatMessage = chatMessageRepository.findById(chattingUpdateRequestDto.getId())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_MESSAGE));
		chatMessageRepository.save(chattingUpdateRequestDto.toEntity(chatMessage));
	}

	@Override // 일대일 채팅인지 확인하는 api
	public CheckOneToOneChatResponseDto checkOnetoOneChat(String userId1, String userId2) {
		ChatRoom chatroom = chatRoomRepository.findOneToOneChatRoom(userId1, userId2).orElse(null);
		if (chatroom == null) {
			return CheckOneToOneChatResponseDto.toDto(false, null);
		}
		return CheckOneToOneChatResponseDto.toDto(true, chatroom.getId());
	}

	@Override // 채팅방 이름 업데이트
	public void updateChatRoomName(ChatRoomNameUpdateRequestDto chatRoomNameUpdateRequestDto) {
		ChatRoom chatRoom = chatRoomRepository.findById(chatRoomNameUpdateRequestDto.getId())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CHATROOM));
		chatRoomRepository.save(
			ChatRoomNameUpdateRequestDto.toEntity(chatRoom, chatRoomNameUpdateRequestDto.getRoomName()));
	}

	@Override // 마지막 읽은 메세지가 무엇인지 체크하는 api
	public Flux<LastReadChatMessageResponseDto> lastReadMessage(String roomId) {
		ChangeStreamOptions options = ChangeStreamOptions.builder().build();

		return reactiveMongoTemplate.changeStream("chat_room", options, ChatRoom.class)
			.map(ChangeStreamEvent::getBody)
			.flatMap(chatRoom -> {
				return Flux.fromIterable(chatRoom.getParticipants())
					.map(participant -> LastReadChatMessageResponseDto.toDto(participant.getUserId(),
						participant.getLastReadChatMessage()));
			});
	}

	@Override // 채팅방 삭제 api
	public void deleteChatRoom(String roomId) { // 채팅방 삭제 코드
		ChatRoom chatRoom = chatRoomRepository.findById(roomId)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CHATROOM));
		chatMessageRepository.deleteAllByRoomId(roomId);
		chatRoomRepository.deleteById(chatRoom.getId());
	}

	@Override // 채팅방 입장 api
	public ParticipantListDto updateEnterTime(String roomId, String userId) {
		ChatRoom chatRoom = chatRoomRepository.findById(roomId)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CHATROOM));

		Participant participant = chatRoom.getParticipants()
			.stream()
			.filter(p -> p.getUserId().equals(userId))
			.findFirst()
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_IN_USER));

		ChatMessage lastMessage = chatMessageRepository.findTopByRoomIdOrderByCreatedAtDesc(roomId).orElse(null);
		participant.updateLastLeaveTime(LocalDateTime.now());
		if (lastMessage != null) {
			participant.updateLastReadChatMessage(lastMessage.getId());
		}
		participant.updateLastEnterTime(LocalDateTime.now());
		chatRoomRepository.save(chatRoom);

		return ParticipantListDto.toDto(chatRoom);
	}

	@Override // 채팅방 퇴장(삭제아님) api
	public void updateLeaveTime(String roomId, String userId) {
		ChatRoom chatRoom = chatRoomRepository.findById(roomId)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_CHATROOM));

		Participant participant = chatRoom.getParticipants()
			.stream()
			.filter(p -> p.getUserId().equals(userId))
			.findFirst()
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_IN_USER));

		ChatMessage lastMessage = chatMessageRepository.findTopByRoomIdOrderByCreatedAtDesc(roomId).orElse(null);
		participant.updateLastLeaveTime(LocalDateTime.now());

		if (lastMessage != null) {
			participant.updateLastReadChatMessage(lastMessage.getId());
		}

		chatRoomRepository.save(chatRoom);
	}

}