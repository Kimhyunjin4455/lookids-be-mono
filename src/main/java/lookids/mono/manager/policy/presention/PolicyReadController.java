package lookids.mono.manager.policy.presention;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lookids.mono.common.entity.BaseResponse;
import lookids.mono.manager.policy.application.PolicyService;
import lookids.mono.manager.policy.vo.out.PolicyResponseVo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager-service/read/policy")
public class PolicyReadController {

	private final PolicyService policyService;

	@Operation(summary = "policy 조회 API", description = "policy 조회 API")
	@GetMapping
	public BaseResponse<PolicyResponseVo> readPolicy(@RequestParam String policyCode) {
		return new BaseResponse<>(policyService.readPolicy(policyCode).toVo());
	}

}
