package lookids.mono.map.presentaion;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lookids.mono.common.entity.BaseResponse;
import lookids.mono.map.application.MapService;
import lookids.mono.map.dto.in.PinReadDto;
import lookids.mono.map.dto.out.DetailPinResponseDto;
import lookids.mono.map.dto.out.PinResponseDto;
import lookids.mono.map.vo.out.DetailPinResponseVo;
import lookids.mono.map.vo.out.PinResponseVo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/map-service/read/map")
public class MapReadController {

	private final MapService mapService;

	@Operation(summary = "ReadPin API", description = "ReadPin API, " + "ha, oa: 좌하단 위도 경도, pa, qa: 우상단 위도 경도")
	@GetMapping
	public BaseResponse<List<PinResponseVo>> readPin(@RequestHeader String uuid, @RequestParam Double ha,
		@RequestParam Double oa, @RequestParam Double pa, @RequestParam Double qa) {

		PinReadDto pinReadDto = PinReadDto.toDto(ha, oa, pa, qa);
		List<PinResponseDto> pinResponseDto = mapService.readPin(uuid, pinReadDto);

		return new BaseResponse<>(pinResponseDto.stream().map(PinResponseDto::toVo).toList());
	}

	@Operation(summary = "ReadBasicPin API", description = "ReadBasicPin API 서비스가 기본 제공하는 핀 조회, "
		+ "ha, oa: 좌하단 위도 경도, pa, qa: 우상단 위도 경도")
	@GetMapping("/basic")
	public BaseResponse<List<PinResponseVo>> readBasicPin(@RequestParam String category, @RequestParam Double ha,
		@RequestParam Double oa, @RequestParam Double pa, @RequestParam Double qa) {

		PinReadDto pinReadDto = PinReadDto.toDto(ha, oa, pa, qa);
		List<PinResponseDto> pinResponseDto = mapService.readBasicPin(category, pinReadDto);

		return new BaseResponse<>(pinResponseDto.stream().map(PinResponseDto::toVo).toList());
	}

	@Operation(summary = "ReadDetailPin API", description = "ReadDetailPin API 핀 상세 조회")
	@GetMapping("/detail")
	public BaseResponse<DetailPinResponseVo> readDetailPin(@RequestParam String pinCode) {

		DetailPinResponseDto detailPinResponseDto = mapService.readDetailPin(pinCode);
		return new BaseResponse<>(detailPinResponseDto.toVo(detailPinResponseDto));

	}

}