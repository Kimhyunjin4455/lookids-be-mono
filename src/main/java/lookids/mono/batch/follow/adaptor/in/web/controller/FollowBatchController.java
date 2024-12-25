package lookids.mono.batch.follow.adaptor.in.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.batch.follow.adaptor.in.web.mapper.FollowWebVoMapper;
import lookids.mono.batch.follow.adaptor.in.web.out.FollowCountResponseVo;
import lookids.mono.batch.follow.application.port.in.FollowCountReadUseCase;
import lookids.mono.common.entity.BaseResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/batch-service/read/follow")
public class FollowBatchController {
	private final FollowCountReadUseCase followCountReadUseCase;
	private final FollowWebVoMapper followWebVoMapper;

	@Operation(summary = "readFollowCount API", description = "readFollowCount API 입니다.")
	@GetMapping()
	public BaseResponse<FollowCountResponseVo> readFollowCount(@RequestParam(value = "uuid") String uuid) {
		return new BaseResponse<>(
			followWebVoMapper.toFollowCountResponseVo(followCountReadUseCase.readFollowCount(uuid)));
	}
}
