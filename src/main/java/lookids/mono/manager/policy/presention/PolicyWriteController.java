package lookids.manager.policy.presention;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lookids.manager.common.entity.BaseResponse;
import lookids.manager.common.entity.BaseResponseStatus;
import lookids.manager.policy.application.PolicyService;
import lookids.manager.policy.dto.in.PolicyRequestDto;
import lookids.manager.policy.vo.in.PolicyRequestVo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/write/policy")
public class PolicyWriteController {

	private final PolicyService policyService;

	@Operation(summary = "policy 등록 API", description = "policy 등록 API")
	@PostMapping
	public BaseResponse<Void> createPolicy(@RequestBody PolicyRequestVo policyRequestVo) {
		policyService.createPolicy(PolicyRequestDto.toDto(policyRequestVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "policy 삭제 API", description = "policy 삭제 API")
	@DeleteMapping
	public BaseResponse<Void> deletePolicy(@RequestParam String policyCode) {
		policyService.deletePolicy(policyCode);
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

}
