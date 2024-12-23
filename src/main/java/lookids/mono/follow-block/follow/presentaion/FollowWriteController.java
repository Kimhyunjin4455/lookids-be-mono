package Lookids.follow.presentaion;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Lookids.common.entity.BaseResponse;
import Lookids.follow.application.FollowService;
import Lookids.follow.dto.in.FollowRequestDto;
import Lookids.follow.vo.in.FollowRequestVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/write")
public class FollowWriteController {

	private final FollowService followService;

	@Operation(summary = "createFollow API", description = "createFollow API")
	@PutMapping("/follow")
	public BaseResponse<Boolean> createFollow(@RequestHeader String uuid, @RequestBody FollowRequestVo followRequestVo) {
		return new BaseResponse<>(followService.createFollow(FollowRequestDto.toDto(uuid, followRequestVo)));
	}

}
