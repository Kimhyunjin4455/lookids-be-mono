package lookids.manager.policy.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lookids.manager.policy.domain.Policy;
import lookids.manager.policy.vo.out.PolicyResponseVo;

@Getter
@ToString
public class PolicyResponseDto {

	private String policyName;
	private String content;

	@Builder
	public PolicyResponseDto(
		String policyName,
		String content
	) {
		this.policyName = policyName;
		this.content = content;
	}

	public static PolicyResponseDto toDto(Policy policy) {
		return PolicyResponseDto.builder()
			.policyName(policy.getPolicyName())
			.content(policy.getContent())
			.build();
	}

	public PolicyResponseVo toVo() {
		return PolicyResponseVo.builder()
			.policyName(policyName)
			.content(content)
			.build();
	}

}
