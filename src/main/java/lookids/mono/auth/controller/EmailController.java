package lookids.mono.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.auth.dto.out.KeyResponseDto;
import lookids.mono.auth.service.EmailService;
import lookids.mono.auth.vo.in.KeyRequestVo;
import lookids.mono.auth.vo.out.KeyResponseVo;
import lookids.mono.common.entity.BaseResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth-service/auth")
public class EmailController {

	private final EmailService emailService;

	@Operation(summary = "send-email API", description = "sendemail API 입니다. key값에 이메일 수신지 주소를 입력하세요.", tags = {"Mail"})
	@PostMapping("/send-email")
	public BaseResponse<KeyResponseVo> sendEmail(@RequestBody KeyRequestVo keyRequestVo) {
		// email 전송 후 api 테스트 프로그램에서 최소한의 응답값을 받기 위해 + 반환타입 통일성 위해 BaseResponse<KeyResponseVo> 사용

		KeyResponseDto keyResponseDto = emailService.sendSimpleEmail(keyRequestVo.getKey());
		return new BaseResponse<>(KeyResponseDto.toVo(keyResponseDto));
	}

}
