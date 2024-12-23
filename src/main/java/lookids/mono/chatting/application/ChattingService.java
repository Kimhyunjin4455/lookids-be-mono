package lookids.chatting.chatting.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lookids.chatting.chatting.dto.in.ChatRoomNameUpdateRequestDto;
import lookids.chatting.chatting.dto.in.ChatRoomRequestDto;
import lookids.chatting.chatting.dto.in.ChattingRequestDto;
import lookids.chatting.chatting.dto.in.ChattingUpdateRequestDto;
import lookids.chatting.chatting.dto.out.ChatRoomResponseDto;
import lookids.chatting.chatting.dto.out.ChattingResponseDto;
import lookids.chatting.chatting.dto.out.CheckOneToOneChatResponseDto;
import lookids.chatting.chatting.dto.out.LastReadChatMessageResponseDto;
import lookids.chatting.chatting.dto.out.ParticipantListDto;
import lookids.chatting.chatting.dto.out.RoomIdResponseDto;
import lookids.chatting.common.utils.CursorPage;
import reactor.core.publisher.Flux;

public interface ChattingService {
	RoomIdResponseDto createChatRoom(ChatRoomRequestDto chatRoomRequestDto);

	void createChatMessage(ChattingRequestDto chattingRequestDto);

	Page<ChatRoomResponseDto> readChatRoomsByUserId(String userId, Pageable pageable);

	Page<ChattingResponseDto> readChatMessageByRoomIdPage(String roomId, Pageable pageable);

	CursorPage<ChatRoomResponseDto> readChatRoomsByUserIdCursorPage(String userId, String lastId, int size, int page);

	CursorPage<ChattingResponseDto> readChatMessageByRoomIdCursorPage(String roomId, String lastId, int size, int page);

	Flux<ChatRoomResponseDto> readReactiveChatRoomsByUserId(String userId);

	Flux<ChattingResponseDto> readReactiveChatMessageByRoomId(String roomId);

	void deleteChatRoom(String roomId);

	ParticipantListDto updateEnterTime(String roomId, String userId);

	void updateLeaveTime(String roomId, String userId);

	Flux<LastReadChatMessageResponseDto> lastReadMessage(String roomId);

	void updateChatMessage(ChattingUpdateRequestDto chattingUpdateRequestDto);

	CheckOneToOneChatResponseDto checkOnetoOneChat(String userId1, String userId2);

	void updateChatRoomName(ChatRoomNameUpdateRequestDto chatRoomNameUpdateRequestDto);

}
