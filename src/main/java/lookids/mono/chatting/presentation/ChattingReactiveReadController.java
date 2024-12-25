package lookids.mono.chatting.presentation;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.chatting.application.ChattingService;
import lookids.mono.chatting.dto.out.ChatRoomResponseDto;
import lookids.mono.chatting.dto.out.ChattingResponseDto;
import lookids.mono.chatting.dto.out.LastReadChatMessageResponseDto;
import lookids.mono.chatting.vo.out.ChatRoomResponseVo;
import lookids.mono.chatting.vo.out.ChattingResponseVo;
import lookids.mono.chatting.vo.out.LastReadMessageResponseVo;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chatting-service/read/chat")
@Slf4j
public class ChattingReactiveReadController {

	private final ChattingService chattingService;

	@GetMapping(value = "/reactive/rooms/{userId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ChatRoomResponseVo> readReactiveUserChatRooms(@PathVariable String userId) {
		return chattingService.readReactiveChatRoomsByUserId(userId)
			.map(ChatRoomResponseDto::toVo)
			.subscribeOn(Schedulers.boundedElastic())
			.timeout(Duration.ofMinutes(1)) // 1분 동안 새로운 이벤트가 없으면 연결 종료
			.doOnError(throwable -> {
				// 타임아웃 발생 시 로그 기록
				if (throwable instanceof TimeoutException) {
					log.info("SSE 타임아웃: 사용자 {}의 채팅방 연결이 종료되었습니다.", userId);
				}
			});
	}

	@GetMapping(value = "/reactive/{roomId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ChattingResponseVo> readReactiveNewChatMessageByRoomId(@PathVariable String roomId) {
		return chattingService.readReactiveChatMessageByRoomId(roomId)
			.map(ChattingResponseDto::toVo)
			.subscribeOn(Schedulers.boundedElastic())
			.timeout(Duration.ofMinutes(1)) // 1분 동안 새로운 메시지가 없으면 연결 종료
			.doOnError(throwable -> {
				// 타임아웃 발생 시 로그 기록
				if (throwable instanceof TimeoutException) {
					log.info("SSE 타임아웃: 방 {}의 메시지 연결이 종료되었습니다.", roomId);
				}
			});
	}

	@GetMapping(value = "/chatroom/last-read/{roomId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<LastReadMessageResponseVo> lastReadMessage(@PathVariable String roomId) {
		return chattingService.lastReadMessage(roomId)
			.map(LastReadChatMessageResponseDto::toVo)
			.subscribeOn(Schedulers.boundedElastic())
			.timeout(Duration.ofMinutes(1)) // 1분 동안 새로운 읽음 상태가 없으면 연결 종료
			.doOnError(throwable -> {
				// 타임아웃 발생 시 로그 기록
				if (throwable instanceof TimeoutException) {
					log.info("SSE 타임아웃: 방 {}의 마지막 읽음 메시지 연결이 종료되었습니다.", roomId);
				}
			});
	}
}
