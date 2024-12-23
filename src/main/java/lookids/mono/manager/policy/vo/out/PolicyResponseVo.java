package lookids.manager.policy.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PolicyResponseVo {

	private String policyName;
	private String content;

	@Builder
	public PolicyResponseVo(
		String policyName,
		String content
	) {
		this.policyName = policyName;
		this.content = content;
	}

}
