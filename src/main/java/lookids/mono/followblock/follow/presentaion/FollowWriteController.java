package lookids.mono.followblock.follow.presentaion;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lookids.mono.common.entity.BaseResponse;
import lookids.mono.followblock.follow.application.FollowService;
import lookids.mono.followblock.follow.dto.in.FollowRequestDto;
import lookids.mono.followblock.follow.vo.in.FollowRequestVo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow-block-service/write")
public class FollowWriteController {

	private final FollowService followService;

	@Operation(summary = "createFollow API", description = "createFollow API")
	@PutMapping("/follow")
	public BaseResponse<Boolean> createFollow(@RequestHeader String uuid,
		@RequestBody FollowRequestVo followRequestVo) {
		return new BaseResponse<>(followService.createFollow(FollowRequestDto.toDto(uuid, followRequestVo)));
	}

}
