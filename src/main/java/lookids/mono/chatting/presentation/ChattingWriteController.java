package lookids.chatting.chatting.presentation;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lookids.chatting.chatting.application.ChattingServiceImpl;
import lookids.chatting.chatting.dto.in.ChatRoomNameUpdateRequestDto;
import lookids.chatting.chatting.dto.in.ChatRoomRequestDto;
import lookids.chatting.chatting.dto.in.ChattingRequestDto;
import lookids.chatting.chatting.dto.in.ChattingUpdateRequestDto;
import lookids.chatting.chatting.vo.in.ChatRoomNameUpdateRequestVo;
import lookids.chatting.chatting.vo.in.ChatRoomRequestVo;
import lookids.chatting.chatting.vo.in.ChattingRequestVo;
import lookids.chatting.chatting.vo.in.ChattingUpdateRequestVo;
import lookids.chatting.chatting.vo.out.ParticipantListVo;
import lookids.chatting.chatting.vo.out.RoomIdResponseVo;
import lookids.chatting.common.entity.BaseResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChattingWriteController {
	private final ChattingServiceImpl chattingService;

	@PostMapping("/room") // 채팅 방 만드는 API`
	public BaseResponse<RoomIdResponseVo> createChatRoom(@RequestBody ChatRoomRequestVo chatRoomRequestVo) {
		return new BaseResponse<>(chattingService.createChatRoom(ChatRoomRequestDto.toDto(chatRoomRequestVo)).toVo());
	}

	@PostMapping("/messages") //채팅 메세지 만드는 API
	public BaseResponse<Void> createChatMessage(@RequestBody ChattingRequestVo chattingRequestVo) {
		chattingService.createChatMessage(ChattingRequestDto.toDto(chattingRequestVo));
		return new BaseResponse<>();
	}

	@DeleteMapping("/{roomId}")//채팅방 삭제
	public BaseResponse<Void> deleteChatRoom(@PathVariable String roomId) {
		chattingService.deleteChatRoom(roomId);
		return new BaseResponse<>();
	}

	@PutMapping("/chatMessage")
	public BaseResponse<Void> updateChatMessage(@RequestBody ChattingUpdateRequestVo chattingUpdateRequestVo) {
		chattingService.updateChatMessage(ChattingUpdateRequestDto.toDto(chattingUpdateRequestVo));
		return new BaseResponse<>();
	}

	@PutMapping("/enter/{roomId}/{userId}")
	public BaseResponse<ParticipantListVo> updateEnterTime(@PathVariable String roomId, @PathVariable String userId) {

		return new BaseResponse<>(chattingService.updateEnterTime(roomId, userId).toVo());
	}

	@PutMapping("/leave/{roomId}/{userId}")
	public BaseResponse<Void> updateLeaveTime(@PathVariable String roomId, @PathVariable String userId) {
		chattingService.updateLeaveTime(roomId, userId);
		return new BaseResponse<>();
	}

	@PutMapping("room-name")
	public BaseResponse<Void> updateRoomName(@RequestBody ChatRoomNameUpdateRequestVo chatRoomNameUpdateRequestVo) {
		chattingService.updateChatRoomName(ChatRoomNameUpdateRequestDto.toDto(chatRoomNameUpdateRequestVo));
		return new BaseResponse<>();
	}
}
