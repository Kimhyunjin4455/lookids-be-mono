package lookids.mono.user.point.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.common.entity.BaseResponse;
import lookids.mono.user.point.application.PointService;
import lookids.mono.user.point.dto.out.PointResponseDto;
import lookids.mono.user.point.vo.out.PointResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user-service/read/point")
public class PointReadController {

	private final PointService pointService;

	@Operation(summary = "readPointList API", description = "readPointList API 입니다.")
	@GetMapping()
	public BaseResponse<List<PointResponseVo>> readPointList(@RequestParam(value = "userUuid") String userUuid) {
		List<PointResponseDto> pointResponseDtoList = pointService.readPointList(userUuid);
		return new BaseResponse<>(pointResponseDtoList.stream().map(PointResponseDto::toVo).toList());
	}

	@Operation(summary = "readPointType API", description = "readPointType API 입니다.")
	@GetMapping("/type")
	public BaseResponse<List<PointResponseVo>> readPointType(@RequestParam(value = "userUuid") String userUuid,
		@RequestParam(value = "type") Boolean type) {
		List<PointResponseDto> pointResponseDtoList = pointService.readPointType(userUuid, type);
		return new BaseResponse<>(pointResponseDtoList.stream().map(PointResponseDto::toVo).toList());
	}
}
