package lookids.mono.chatting.presentation;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lookids.mono.chatting.application.ChattingService;
import lookids.mono.chatting.dto.out.ChatRoomResponseDto;
import lookids.mono.chatting.dto.out.ChattingResponseDto;
import lookids.mono.chatting.vo.out.ChatRoomResponseVo;
import lookids.mono.chatting.vo.out.ChattingResponseVo;
import lookids.mono.chatting.vo.out.CheckOneToOneChatResponseVo;
import lookids.mono.common.entity.BaseResponse;
import lookids.mono.common.entity.BaseResponseStatus;
import lookids.mono.common.utils.CursorPage;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chatting-service/read/chat")
public class ChattingReadController {

	private final ChattingService chattingService;

	@GetMapping(value = "/rooms/{userId}")
	public BaseResponse<Page<ChatRoomResponseVo>> readUserChatRoomsPage(@PathVariable String userId,
		@RequestParam(required = false, defaultValue = "0") int page) {

		Pageable defaultPageable = PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "updatedAt"));
		return new BaseResponse<>(
			chattingService.readChatRoomsByUserId(userId, defaultPageable).map(ChatRoomResponseDto::toVo));
	}

	@GetMapping(value = "cursor-page/rooms/{userId}")
	public BaseResponse<CursorPage<ChatRoomResponseVo>> readUserChatRoomsCursorPage(@PathVariable String userId,
		@RequestParam(required = false) String lastId) {
		CursorPage<ChatRoomResponseDto> chatRoom = chattingService.readChatRoomsByUserIdCursorPage(userId, lastId, 20,
			0);

		if (chatRoom.getContent().isEmpty()) {
			return new BaseResponse<>(HttpStatus.NOT_FOUND, // 404 Not Found
				BaseResponseStatus.NO_EXIST_MESSAGE.isSuccess(), BaseResponseStatus.NO_EXIST_MESSAGE.getMessage(),
				BaseResponseStatus.NO_EXIST_MESSAGE.getCode(), null);
		}

		List<ChatRoomResponseVo> voList = chatRoom.getContent().stream().map(ChatRoomResponseDto::toVo) // toVo 호출
			.toList();

		return new BaseResponse<>(
			new CursorPage<>(voList, chatRoom.getNextCursor(), chatRoom.hasNext(), chatRoom.getPageSize(),
				chatRoom.getPage()));
	}

	@GetMapping(value = "/{roomId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public BaseResponse<Page<ChattingResponseVo>> readChatByRoomIdPage(@PathVariable String roomId,
		@RequestParam(required = false, defaultValue = "0") int page) {

		Pageable defaultPageable = PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "createdAt"));
		return new BaseResponse<>(
			chattingService.readChatMessageByRoomIdPage(roomId, defaultPageable).map(ChattingResponseDto::toVo));
	}

	@GetMapping(value = "cursor-page/{roomId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public BaseResponse<CursorPage<ChattingResponseVo>> readChatByRoomIdCursorPage(@PathVariable String roomId,
		@RequestParam(required = false) String lastId) {
		CursorPage<ChattingResponseDto> chatMessages = chattingService.readChatMessageByRoomIdCursorPage(roomId, lastId,
			20, 0);
		// 리뷰가 없을 경우 다른 응답 메시지 반환
		if (chatMessages == null || chatMessages.getContent().isEmpty()) {
			return new BaseResponse<>(HttpStatus.NOT_FOUND, // 404 Not Found
				BaseResponseStatus.NO_EXIST_MESSAGE.isSuccess(), BaseResponseStatus.NO_EXIST_MESSAGE.getMessage(),
				BaseResponseStatus.NO_EXIST_MESSAGE.getCode(), null // 리뷰가 없으므로 null 반환
			);
		}

		List<ChattingResponseVo> voList = chatMessages.getContent().stream().map(ChattingResponseDto::toVo) // toVo 호출
			.toList();

		return new BaseResponse<>(
			new CursorPage<>(voList, chatMessages.getNextCursor(), chatMessages.hasNext(), chatMessages.getPageSize(),
				chatMessages.getPage()));
	}

	@GetMapping(value = "/check-one-to-one-chatroom/{userId1}/{userId2}")
	public BaseResponse<CheckOneToOneChatResponseVo> checkOneToOneChatRoom(@PathVariable String userId1,
		@PathVariable String userId2) {
		return new BaseResponse<>(chattingService.checkOnetoOneChat(userId1, userId2).toVo());
	}

}
