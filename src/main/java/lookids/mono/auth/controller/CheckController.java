package lookids.auth.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.auth.auth.dto.in.EmailVerifyRequestDto;
import lookids.auth.auth.dto.in.KeyRequestDto;
import lookids.auth.auth.dto.in.PasswordRequestDto;
import lookids.auth.auth.dto.out.KeyResponseDto;
import lookids.auth.auth.dto.out.LoginIdFindResponseDto;
import lookids.auth.auth.service.CheckService;
import lookids.auth.auth.vo.in.EmailVerifyRequestVo;
import lookids.auth.auth.vo.in.KeyRequestVo;
import lookids.auth.auth.vo.in.PasswordRequestVo;
import lookids.auth.auth.vo.out.KeyResponseVo;
import lookids.auth.auth.vo.out.LoginIdFindResponseVo;
import lookids.auth.common.entity.BaseResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class CheckController {
	// 검증, 아이디 찾기를 담당하는 Controller
	private final CheckService checkService;

	@Operation(summary = "verify-loginid API", description = "verifyloginid API 입니다. 존재하는 이메일 입력 시 중복에 관한 메세지를 나타냅니다.", tags = {"Verify"})
	@PostMapping("/verify-loginid")
	public BaseResponse<KeyResponseVo> verifyLoginId(
		@RequestBody KeyRequestVo keyRequestVo) {
		KeyRequestDto keyRequestDto = KeyRequestDto.toDto(keyRequestVo);

		return new BaseResponse<>(KeyResponseDto.toVo(
			checkService.verifyLoginId(keyRequestDto)));
	}

	@Operation(summary = "verify-password API", description = "verifypassword API 입니다. 틀린 비밀번호 입력시 이와 관련된 메세지를 나타냅니다.", tags = {"Verify"})
	@PostMapping("/verify-password")
	public BaseResponse<KeyResponseVo> verifyPassword(
		@RequestHeader("uuid") String uuid, //
		@RequestBody PasswordRequestVo verifyPasswordRequestVo) {
		PasswordRequestDto passwordRequestDto = PasswordRequestDto.toDto(verifyPasswordRequestVo, uuid);

		return new BaseResponse<>(KeyResponseDto.toVo(
			checkService.verifyPassword(passwordRequestDto)));
	}

	@Operation(summary = "verify-email API", description = "이메일 중복 확인 api입니다. result의 verification값이 false라면 이메일이 중복되었다는 뜻입니다.", tags = {"Verify"})
	@PostMapping("/verify-email")
	public BaseResponse<KeyResponseVo> verifyEmail(
		@RequestBody KeyRequestVo keyRequestVo) {
		KeyRequestDto keyRequestDto = KeyRequestDto.toDto(keyRequestVo);

		return new BaseResponse<>(KeyResponseDto.toVo(
			checkService.verifyEmail(keyRequestDto)));
	}

	@Operation(summary = "check-verification-code API", description = "인증코드 확인 api 입니다.", tags = {"Verify"})
	@PostMapping("/check-verification-code")
	public BaseResponse<KeyResponseVo> checkVerificationCode(
		@RequestBody EmailVerifyRequestVo emailVerifyRequestVo
	) {
		EmailVerifyRequestDto emailVerifyRequestDto = EmailVerifyRequestDto.toDto(emailVerifyRequestVo);
		return new BaseResponse<>(KeyResponseDto.toVo(
			checkService.checkVerificationCode(emailVerifyRequestDto)));
	}

	@Operation(summary = "find-id-by-email API", description = "find-id-by-email API 입니다. 이메일을 입력하세요.(주의: 이 api는 인증코드 검증 후 호출되어야 합니다.)",  tags = {"Verify"})
	@PostMapping("/find-id-by-email")
	public BaseResponse<LoginIdFindResponseVo> findIdByEmail(
		@RequestBody KeyRequestVo keyRequestVo) {
		KeyRequestDto keyRequestDto = KeyRequestDto.toDto(keyRequestVo);
		LoginIdFindResponseDto loginIdFindResponseDto = checkService.findIdByEmail(keyRequestDto);
		return new BaseResponse<>(loginIdFindResponseDto.toVo(loginIdFindResponseDto));
	}

	@Operation(summary = "put-password API", description = "putpassword API 입니다. 비밀번호 재설정 하세요.(주의: 이 api는 인증코드 검증 후 호출되어야 합니다.)",  tags = {"Verify"})
	@PutMapping("/password")
	public BaseResponse<Void> putPassword(
		@RequestHeader("uuid") String uuid,
		@RequestBody PasswordRequestVo passwordRequestVo) {
		checkService.putPassword(PasswordRequestDto.toDto(passwordRequestVo, uuid));
		return new BaseResponse<>();
	}
}
