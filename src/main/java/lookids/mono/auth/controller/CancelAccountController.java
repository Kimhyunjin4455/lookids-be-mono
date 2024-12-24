package lookids.mono.auth.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.auth.service.CancelAccountService;
import lookids.mono.common.entity.BaseResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth-service/auth")
public class CancelAccountController {
	private final CancelAccountService cancelAccountService;

	@Operation(summary = "soft-cancel-account API", description = "soft-cancel-account API 입니다. 회원정보에 대해 탈퇴 처리를 진행합니다. 상태처리만 변경하고 데이터를 삭제하지는 않습니다.", tags = {
		"Cancel Account"})
	@PutMapping("/soft-cancel-account")
	public BaseResponse<Void> softCancelAccount(@RequestHeader("uuid") String uuid) {
		cancelAccountService.deleteAccountState(uuid);
		return new BaseResponse<>();
	}

	@Operation(summary = "soft-cancel-socialAccount API", description = "soft-cancel-socialAccount API 입니다. 회원정보에 대해 탈퇴 처리를 진행합니다. 상태처리만 변경하고 데이터를 삭제하지는 않습니다.", tags = {
		"Cancel Account"})
	@PutMapping("/soft-cancel-social-account")
	public BaseResponse<Void> softCancelSocialAccount(@RequestHeader("uuid") String uuid) {
		cancelAccountService.deleteSocialAccountState(uuid);
		return new BaseResponse<>();
	}
}
