package lookids.mono.user.userprofile.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.common.entity.BaseResponse;
import lookids.mono.user.userprofile.application.UserProfileService;
import lookids.mono.user.userprofile.vo.out.UserProfileResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user-service/read/userprofile")
public class UserProfileReadController {

	private final UserProfileService userProfileService;

	@Operation(summary = "readUserProfile API", description = "readUserProfile API 입니다.")
	@GetMapping()
	public BaseResponse<UserProfileResponseVo> readUserProfile(@RequestParam(value = "userUuid") String userUuid) {
		return new BaseResponse<>(userProfileService.readUserProfile(userUuid).toVo());
	}

	@Operation(summary = "readUserProfile API", description = "readUserProfile API 입니다.")
	@GetMapping("/find/{nickname}-{tag}")
	public BaseResponse<UserProfileResponseVo> readUserProfileWithTag(@PathVariable(value = "nickname") String nickname,
		@PathVariable(value = "tag") String tag) {
		return new BaseResponse<>(userProfileService.readUserProfileWithTag(nickname, tag).toVo());
	}
}
