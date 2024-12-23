package lookids.manager.policy.application;

import lookids.manager.policy.dto.in.PolicyRequestDto;
import lookids.manager.policy.dto.out.PolicyResponseDto;

public interface PolicyService {

	void createPolicy(PolicyRequestDto policyRequestDto);
	PolicyResponseDto readPolicy(String policyCode);
	void deletePolicy(String policyCode);

}
