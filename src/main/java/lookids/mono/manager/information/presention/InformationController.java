package lookids.manager.information.presention;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lookids.manager.common.entity.BaseResponse;
import lookids.manager.common.entity.BaseResponseStatus;
import lookids.manager.information.application.InformationService;
import lookids.manager.information.dto.in.InformationRequestDto;
import lookids.manager.information.dto.in.InformationUpdateRequestDto;
import lookids.manager.information.dto.out.InformationResponseDto;
import lookids.manager.information.vo.in.InformationRequestVo;
import lookids.manager.information.vo.in.InformationUpdateRequestVo;
import lookids.manager.information.vo.out.InformationResponseVo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/information")
public class InformationController {

	private final InformationService informationService;

	@Operation(summary = "information 등록 API", description = "information 등록 API 입니다.", tags = {"information"})
	@PostMapping
	public BaseResponse<Void> createInformation(@RequestBody InformationRequestVo informationRequestVo) {
		informationService.createInformation(InformationRequestDto.toDto(informationRequestVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}
	@Operation(summary = "InformationList 조회 API", description = "InformationList 조회 API 입니다.", tags = {"information"})
	@GetMapping("/manager")
	public BaseResponse<List<InformationResponseVo>> readInformationList(String managerUuid) {
		List<InformationResponseDto> informationResponseDtoList = informationService.readInformationList(managerUuid);
		return new BaseResponse<>(informationResponseDtoList.stream().map(InformationResponseDto::toVo).toList());
	}

	@Operation(summary = "information 조회 API", description = "information 조회 API 입니다.", tags = {"information"})
	@GetMapping()
	public BaseResponse<InformationResponseVo> readInformation(@RequestParam String feedCode) {
		return new BaseResponse<>(informationService.readInformation(feedCode).toVo());
	}

	@Operation(summary = "Information 수정 API", description = "Information 수정 API 입니다.", tags = {"information"})
	@PutMapping()
	public BaseResponse<Void> updateInformation(
		@RequestParam String feedCode,
		@RequestBody InformationUpdateRequestVo informationUpdateRequestVo) {
		informationService.updateInformation(feedCode, InformationUpdateRequestDto.toDto(feedCode, informationUpdateRequestVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "information 삭제 API", description = "information 삭제 API 입니다.", tags = {"information"})
	@DeleteMapping
	public BaseResponse<Void> deleteInformation(@RequestParam String feedCode) {
		informationService.deleteInformation(feedCode);
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

}
