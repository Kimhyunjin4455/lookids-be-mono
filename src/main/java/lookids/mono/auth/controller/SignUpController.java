package lookids.mono.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.auth.dto.in.SignUpRequestDto;
import lookids.mono.auth.service.SignUpService;
import lookids.mono.auth.vo.in.SignUpRequestVo;
import lookids.mono.common.entity.BaseResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth-service/auth")
public class SignUpController {

	private final SignUpService signUpService;

	@Operation(summary = "sign-up API", description = "sign-up API 입니다.", tags = {"Auth"})
	@PostMapping("/sign-up")
	public BaseResponse<Void> signUp(@RequestBody SignUpRequestVo signUpRequestVo) {
		signUpService.signUp(SignUpRequestDto.toDto(signUpRequestVo));
		return new BaseResponse<>();
	}

}
