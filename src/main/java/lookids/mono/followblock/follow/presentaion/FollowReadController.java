package lookids.mono.followblock.follow.presentaion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lookids.mono.common.entity.BaseResponse;
import lookids.mono.followblock.follow.application.FollowService;
import lookids.mono.followblock.follow.dto.out.FollowInfoResponseDto;
import lookids.mono.followblock.follow.vo.out.FollowUserProfileResponseVo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow-block-service/read/")
public class FollowReadController {

	private final FollowService followService;

	@Operation(summary = "readFollow API", description = "readFollow API")
	@GetMapping("follow")
	public BaseResponse<Boolean> readFollow(@RequestHeader String uuid, @RequestParam String targetUuid) {
		return new BaseResponse<>(followService.readFollow(targetUuid, uuid));
	}

	// @Operation(summary = "readFollower API", description = "readFollower API")
	// @GetMapping("follower")
	// public BaseResponse<Slice<FollowResponseVo>> readFollower(@RequestHeader String uuid,
	// 	@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
	// 	Pageable pageable = PageRequest.of(page, size);
	//
	// 	Slice<FollowResponseDto> followResponseDtoSlice = followService.readFollowerList(uuid, pageable);
	// 	Slice<FollowResponseVo> followResponseVoSlice = followResponseDtoSlice.map(FollowResponseDto::toVo);
	//
	// 	return new BaseResponse<>(followResponseVoSlice);
	// }
	//
	// @Operation(summary = "readFollowing API", description = "readFollowing API")
	// @GetMapping("following")
	// public BaseResponse<Slice<FollowResponseVo>> readFollowing(@RequestHeader String uuid,
	// 	@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
	// 	Pageable pageable = PageRequest.of(page, size);
	//
	// 	Slice<FollowResponseDto> followResponseDtoSlice = followService.readFollowingList(uuid, pageable);
	// 	Slice<FollowResponseVo> followResponseVoSlice = followResponseDtoSlice.map(FollowResponseDto::toVo);
	//
	// 	return new BaseResponse<>(followResponseVoSlice);
	// }

	@Operation(summary = "readFollower API", description = "readFollower API")
	@GetMapping("follower")
	public BaseResponse<Page<FollowUserProfileResponseVo>> readFollower(@RequestHeader String uuid,
		@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);

		Page<FollowInfoResponseDto> followerInfo = followService.readFollowerUserProfile(uuid, pageable);
		Page<FollowUserProfileResponseVo> followResponseVoPage = followerInfo.map(FollowInfoResponseDto::toVo);
		System.out.println(followResponseVoPage);

		return new BaseResponse<>(followResponseVoPage);
	}

	@Operation(summary = "readFollowing API", description = "readFollowing API")
	@GetMapping("following")
	public BaseResponse<Page<FollowUserProfileResponseVo>> readFollowing(@RequestHeader String uuid,
		@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
		Pageable pageable = PageRequest.of(page, size);

		Page<FollowInfoResponseDto> followingInfo = followService.readFollowingUserProfile(uuid, pageable);
		Page<FollowUserProfileResponseVo> followResponseVoPage = followingInfo.map(FollowInfoResponseDto::toVo);

		return new BaseResponse<>(followResponseVoPage);
	}

}
