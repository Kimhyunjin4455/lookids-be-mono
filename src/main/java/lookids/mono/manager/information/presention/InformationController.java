package lookids.mono.manager.information.presention;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lookids.mono.common.entity.BaseResponse;
import lookids.mono.common.entity.BaseResponseStatus;
import lookids.mono.manager.information.application.InformationService;
import lookids.mono.manager.information.dto.in.InformationRequestDto;
import lookids.mono.manager.information.dto.in.InformationUpdateRequestDto;
import lookids.mono.manager.information.dto.out.InformationResponseDto;
import lookids.mono.manager.information.vo.in.InformationRequestVo;
import lookids.mono.manager.information.vo.in.InformationUpdateRequestVo;
import lookids.mono.manager.information.vo.out.InformationResponseVo;

@RequiredArgsConstructor
@RestController
@RequestMapping("/manager-service/information")
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
	public BaseResponse<Void> updateInformation(@RequestParam String feedCode,
		@RequestBody InformationUpdateRequestVo informationUpdateRequestVo) {
		informationService.updateInformation(feedCode,
			InformationUpdateRequestDto.toDto(feedCode, informationUpdateRequestVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "information 삭제 API", description = "information 삭제 API 입니다.", tags = {"information"})
	@DeleteMapping
	public BaseResponse<Void> deleteInformation(@RequestParam String feedCode) {
		informationService.deleteInformation(feedCode);
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

}
