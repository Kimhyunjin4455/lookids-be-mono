package lookids.user.agreement.presentation;

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
import lombok.extern.slf4j.Slf4j;
import lookids.user.agreement.application.AgreementService;
import lookids.user.agreement.dto.in.AgreementRequestDto;
import lookids.user.agreement.dto.in.AgreementUpdateDto;
import lookids.user.agreement.vo.in.AgreementRequestVo;
import lookids.user.agreement.vo.in.AgreementUpdateVo;
import lookids.user.common.entity.BaseResponse;
import lookids.user.common.entity.BaseResponseStatus;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/write/agreement")
public class AgreementWriteController {

	private final AgreementService agreementService;

	@Operation(summary = "createAgreement API", description = "createAgreement API 입니다.")
	@PostMapping()
	public BaseResponse<Void> createAgreement(@RequestBody AgreementRequestVo agreementRequestVo) {
		agreementService.createAgreement(AgreementRequestDto.toDto(agreementRequestVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "updateAgreement API", description = "updateAgreement API 입니다.")
	@PutMapping()
	public BaseResponse<Void> updateAgreement(@RequestHeader("uuid") String uuid,
		@RequestBody AgreementUpdateVo agreementUpdateVo) {
		agreementService.updateAgreement(AgreementUpdateDto.toDto(agreementUpdateVo, uuid));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "deleteAgreement API", description = "deleteAgreement API 입니다.")
	@DeleteMapping()
	public BaseResponse<Void> deleteAgreement(@RequestParam(value = "userUuid") String userUuid,
		@RequestParam(value = "policyUuid") String policyUuid) {
		agreementService.deleteAgreement(userUuid, policyUuid);
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "updatePolicy API", description = "updatePolicy API 입니다.")
	@PostMapping("/policy")
	public BaseResponse<Void> updatePolicy(@RequestBody AgreementRequestVo agreementRequestVo) {
		agreementService.updatePolicy(AgreementRequestDto.toDto(agreementRequestVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

}
