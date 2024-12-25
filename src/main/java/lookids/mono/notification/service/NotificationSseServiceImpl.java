package lookids.mono.notification.service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.notification.domain.Notification;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationSseServiceImpl implements NotificationSseService {

	private final Map<String, SseEmitter> clients = new ConcurrentHashMap<>();

	@Override
	public SseEmitter registerClient(String userInfo) {
		// 단일 연결만 유지됨, 추후 여러 기기에서의 리스트를 위해 List<SseEmitter> 적용
		SseEmitter emitter = new SseEmitter(1000L * 60 * 10);

		clients.put(userInfo, emitter);
		log.info("Registered client for user: {}", userInfo);
		log.info("Current clients: {}", clients.keySet());
		log.info("Current emitter: {}", emitter);

		// 더미 데이터 전송 (연결 확인 이벤트)
		try {
			// emitter.send(":\n\n");
			log.info("Sent connection-init event to user: {}", userInfo);
			emitter.send("connect");
			// .data(notification));
			log.info("sse success send event, emitter: {}", emitter);
		} catch (IOException e) {
			log.error("Failed to send initial connection event: {}", e.getMessage());
			clients.remove(userInfo);
		}
		// 클라이언트 연결 종료 시 처리
		emitter.onCompletion(() -> {
			// 클라이언트가 연결을 끊을 때 emitter를 제거하지 않고 유지
			log.info("Client disconnected: {}", userInfo);
			clients.remove(userInfo);
		});

		// // 타임아웃 처리 시 emitter 삭제하지 않음
		// emitter.onTimeout(() -> {
		// 	log.info("Client timeout: {}", userInfo);
		// 	clients.remove(userInfo);
		// });
		//
		// // 오류 처리 시 emitter 삭제하지 않음
		// emitter.onError((e) -> {
		// 	log.error("Client error: {}", e.getMessage());
		// 	clients.remove(userInfo);
		//
		// });

		return emitter;
	}

	@Override
	public void sendSseNotification(String userInfo, Notification notification) {
		SseEmitter emitter = clients.get(userInfo);
		log.info("----------------------------");
		log.info(clients.toString());
		log.info("----------------------------");
		if (emitter != null) {
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.registerModule(new JavaTimeModule());
				objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
				String jsonNotification = objectMapper.writeValueAsString(notification);
				emitter.send(jsonNotification);
				log.info("sse success send event, emitter: {}", emitter);
				log.info("jsonNotification: {}", jsonNotification);
			} catch (Exception e) {
				clients.remove(userInfo);
				log.error("Error while sending {} notification: {}", notification.getType(), e.getMessage());
			}
		} else
			log.info("emitter is null");
	}

	public SseEmitter readExistingEmitter(String userInfo) {
		return clients.get(userInfo); // 이미 등록된 emitter를 반환
	}
}
