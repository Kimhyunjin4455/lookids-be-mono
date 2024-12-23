package lookids.alarm.notification.controller.write;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.alarm.common.entity.BaseResponse;
import lookids.alarm.notification.dto.in.FcmTokenRequestDto;
import lookids.alarm.notification.service.NotificationPushService;
import lookids.alarm.notification.vo.in.FcmTokenRequestVo;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/write/notification")
public class NotificationWriteController {
	private final NotificationPushService notificationPushService;

	@Operation(
		summary = "FCM 토큰 생성 API",
		description = " <p>유저의 푸시 알림을 위해 생성된 FCM 토큰을 DB에 저장합니다.</p> <ul> <li>한 유저에 대해 여러 FCM 토큰이 존재할 수 있습니다.</li> <li>FCM 토큰은 <code>String</code> 값으로 단일 입력되며, 이는 DTO를 통해 변환되어 <code>List</code>로 관리됩니다.</li> </ul> ",
		tags = {"FCM"}
	)
	@PostMapping("/createFcmToken")
	public BaseResponse<Void> createFcmToken(
		@RequestHeader String uuid,
		@RequestBody FcmTokenRequestVo fcmTokenRequestVo) {
		// Service 호출
		notificationPushService.createFcmToken(FcmTokenRequestDto.toDto(uuid, fcmTokenRequestVo));

		return new BaseResponse<>();
	}

	@Operation(
		summary = "FCM 토큰 삭제 API",
		description = "로그아웃 시 해당 유저의 FCM 토큰을 삭제합니다. <li> auth service의 feign client를 통해 호출 될 예정 </li>",
		tags = {"FCM"}
	)
	@PutMapping("/deleteFcmToken")
	public BaseResponse<Void> deleteFcmToken(
		@RequestHeader String uuid,
		@RequestBody FcmTokenRequestVo logoutRequestVo) {
		// 로그아웃된 유저의 UUID와 FCM 토큰을 받는다
		notificationPushService.deleteFcmToken(uuid, logoutRequestVo.getFcmToken());

		return new BaseResponse<>();
	}



}
