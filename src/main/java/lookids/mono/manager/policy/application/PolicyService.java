package lookids.mono.manager.policy.application;

import lookids.mono.manager.policy.dto.in.PolicyRequestDto;
import lookids.mono.manager.policy.dto.out.PolicyResponseDto;

public interface PolicyService {

	void createPolicy(PolicyRequestDto policyRequestDto);

	PolicyResponseDto readPolicy(String policyCode);

	void deletePolicy(String policyCode);

}
