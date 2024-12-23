package lookids.alarm.notification.controller.read;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.alarm.notification.service.NotificationService;
import lookids.alarm.notification.service.NotificationSseService;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/read/notification")
public class NotificationReadController {
	private final NotificationService notificationService;
	private final NotificationSseService notificationSseService;
	//private final NotificationKafkaListener notificationKafkaListener;
	private final Map<String, SseEmitter> clients = new ConcurrentHashMap<>();

	// text/event-stream을 설정하여 서버가 클라이언트로 SSE 스트림을 전송함을 알림
	@Operation(summary = "SSE 알림 API", description = " <p>특정 유저에게 알림을 전하는 Server Sent Event API입니다.</p> <ul> <li>특정 유저에게 알림을 전송합니다.</li> <li>특정 유저에게 알림을 전송하기 위해 유저의 UUID를 입력합니다.</li> <li> userInfo가 uuid, url값에 노출을 피허기 위해 파라미터명 변경</li> </ul> ", tags = {
			"SSE" })
	@GetMapping(value = "/user/sse/{userInfo}",produces = "text/event-stream;charset=UTF-8")
	public SseEmitter sseNotification(@PathVariable String userInfo) {
		log.info("Received userInfo: {}", userInfo);

		return notificationSseService.registerClient(userInfo);

	}
}
