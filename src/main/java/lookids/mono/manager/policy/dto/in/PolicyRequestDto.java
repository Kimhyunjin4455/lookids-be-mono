package lookids.manager.policy.dto.in;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lookids.manager.policy.domain.Policy;
import lookids.manager.policy.vo.in.PolicyRequestVo;

@Getter
@ToString
public class PolicyRequestDto {

	private String policyName;
	private String content;

	@Builder
	public PolicyRequestDto(String policyName, String content) {
		this.policyName = policyName;
		this.content = content;
	}

	public static PolicyRequestDto toDto(PolicyRequestVo policyRequestVo) {
		return PolicyRequestDto.builder()
			.policyName(policyRequestVo.getPolicyName())
			.content(policyRequestVo.getContent())
			.build();
	}

	public Policy toEntity() {
		return Policy.builder()
			.policyName(policyName)
			.content(content)
			.policyCode(UUID.randomUUID().toString())
			.createdAt(LocalDateTime.now())
			.build();
	}

}
