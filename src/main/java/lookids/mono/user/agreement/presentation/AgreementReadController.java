package lookids.user.agreement.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.user.agreement.application.AgreementService;
import lookids.user.agreement.dto.out.AgreementResponseDto;
import lookids.user.agreement.vo.out.AgreementResponseVo;
import lookids.user.common.entity.BaseResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/read/agreement")
public class AgreementReadController {

	private final AgreementService agreementService;

	@Operation(summary = "readAgreementList API", description = "readAgreementList API 입니다.")
	@GetMapping("/all")
	public BaseResponse<List<AgreementResponseVo>> readAgreementList(
		@RequestParam(value = "userUuid") String userUuid) {
		List<AgreementResponseDto> agreementResponseDtoList = agreementService.readAgreementList(userUuid);
		return new BaseResponse<>(agreementResponseDtoList.stream().map(AgreementResponseDto::toVo).toList());
	}

	@Operation(summary = "readPetProfile API", description = "readPetProfile API 입니다.")
	@GetMapping()
	public BaseResponse<AgreementResponseVo> readPetProfile(@RequestParam(value = "userUuid") String userUuid,
		@RequestParam(value = "policyUuid") String policyUuid) {
		return new BaseResponse<>(agreementService.readAgreement(userUuid, policyUuid).toVo());
	}
}
