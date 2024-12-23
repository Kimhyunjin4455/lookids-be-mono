package lookids.manager.policy.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lookids.manager.common.entity.BaseResponseStatus;
import lookids.manager.common.exception.BaseException;
import lookids.manager.policy.dto.in.PolicyRequestDto;
import lookids.manager.policy.dto.out.PolicyResponseDto;
import lookids.manager.policy.infrastructure.PolicyRepository;

@Service
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService{

	private final PolicyRepository policyRepository;

	@Override
	public void createPolicy(PolicyRequestDto policyRequestDto) {
		policyRepository.save(policyRequestDto.toEntity());
	}

	@Override
	public PolicyResponseDto readPolicy(String policyCode) {
		return PolicyResponseDto.toDto(policyRepository.findByPolicyCode(policyCode)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_POLICY)));
	}

	@Override
	public void deletePolicy(String policyCode) {
		policyRepository.deleteByPolicyCode(policyCode);
	}

}
