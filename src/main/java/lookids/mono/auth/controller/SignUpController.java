package lookids.auth.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.auth.auth.dto.in.SignUpRequestDto;
import lookids.auth.auth.service.SignUpService;
import lookids.auth.auth.vo.in.SignUpRequestVo;
import lookids.auth.common.entity.BaseResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class SignUpController {

	private final SignUpService signUpService;

	@Operation(summary = "sign-up API", description = "sign-up API 입니다.", tags = {"Auth"})
	@PostMapping("/sign-up")
	public BaseResponse<Void> signUp(
		@RequestBody SignUpRequestVo signUpRequestVo) {
		signUpService.signUp(SignUpRequestDto.toDto(signUpRequestVo));
		return new BaseResponse<>();
	}

}
