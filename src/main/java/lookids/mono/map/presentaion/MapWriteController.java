package lookids.mono.map.presentaion;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lookids.mono.common.entity.BaseResponse;
import lookids.mono.common.entity.BaseResponseStatus;
import lookids.mono.map.application.MapService;
import lookids.mono.map.dto.in.PinRequestDto;
import lookids.mono.map.dto.in.PinUpdateDto;
import lookids.mono.map.vo.in.PinRequestVo;
import lookids.mono.map.vo.in.PinUpdateVo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/map-service/write/map")
public class MapWriteController {

	private final MapService mapService;

	@Operation(summary = "CreatePin API", description = "CreatePin API")
	@PostMapping
	public BaseResponse<Void> createPin(@RequestHeader String uuid, @RequestBody PinRequestVo pinRequestVo) {
		mapService.createPin(PinRequestDto.toDto(uuid, pinRequestVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "CreateBasicPin API", description = "CreateBasicPin API")
	@PostMapping("/basic")
	public BaseResponse<Void> createBasicPin(@RequestHeader String uuid, @RequestBody PinRequestVo pinRequestVo) {
		mapService.createBasicPin(PinRequestDto.toDto(uuid, pinRequestVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "UpdatePin API", description = "UpdatePin API")
	@PutMapping
	public BaseResponse<Void> updatePin(@RequestBody PinUpdateVo pinUpdateVo) {
		mapService.updatePin(PinUpdateDto.toDto(pinUpdateVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "DeletePin API", description = "DeletePin API")
	@DeleteMapping
	public BaseResponse<Void> deletePin(@RequestParam String pinCode) {
		mapService.deletePin(pinCode);
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

}
