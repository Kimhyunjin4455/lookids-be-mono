package lookids.mono.user.userprofile.presentation;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.common.entity.BaseResponse;
import lookids.mono.common.entity.BaseResponseStatus;
import lookids.mono.user.userprofile.application.UserProfileService;
import lookids.mono.user.userprofile.dto.in.UserProfileImgDto;
import lookids.mono.user.userprofile.dto.in.UserProfileNicknameDto;
import lookids.mono.user.userprofile.dto.in.UserProfileTierDto;
import lookids.mono.user.userprofile.dto.in.UserProfileUpdateDto;
import lookids.mono.user.userprofile.vo.in.UserProfileImgVo;
import lookids.mono.user.userprofile.vo.in.UserProfileNicknameVo;
import lookids.mono.user.userprofile.vo.in.UserProfileTierVo;
import lookids.mono.user.userprofile.vo.in.UserProfileUpdateVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user-service/write/userprofile")
public class UserProfileWriteController {

	private final UserProfileService userProfileService;

	@Operation(summary = "updateUserProfile API", description = "updateUserProfile API 입니다.")
	@PutMapping()
	public BaseResponse<Void> updateUserProfile(@RequestHeader("uuid") String uuid,
		@RequestBody UserProfileUpdateVo userProfileUpdateVo) {
		userProfileService.updateUserProfile(UserProfileUpdateDto.toDto(userProfileUpdateVo, uuid));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "updateUserProfileImg API", description = "updateUserProfileImg API 입니다.")
	@PutMapping("/img")
	public BaseResponse<Void> updateUserProfileImg(@RequestHeader("uuid") String uuid,
		@RequestBody UserProfileImgVo userProfileImgVo) {
		userProfileService.updateUserProfileImage(UserProfileImgDto.toDto(userProfileImgVo, uuid));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "updateUserProfileTier API", description = "updateUserProfileTier API 입니다.")
	@PutMapping("/tier")
	public BaseResponse<Void> updateUserProfileTier(@RequestBody UserProfileTierVo userProfileTierVo) {
		userProfileService.updateUserProfileTier(UserProfileTierDto.toDto(userProfileTierVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "updateUserProfileNickname API", description = "updateUserProfileNickname API 입니다.")
	@PutMapping("/nickname")
	public BaseResponse<Void> updateUserProfileNickname(@RequestHeader("uuid") String uuid,
		@RequestBody UserProfileNicknameVo userProfileNicknameVo) {
		userProfileService.updateUserProfileNickname(UserProfileNicknameDto.toDto(userProfileNicknameVo, uuid));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "deleteUserProfile API", description = "deleteUserProfile API 입니다.")
	@DeleteMapping()
	public BaseResponse<Void> deleteUserProfile(@RequestHeader("uuid") String uuid) {
		userProfileService.deleteUserProfile(uuid);
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

}
