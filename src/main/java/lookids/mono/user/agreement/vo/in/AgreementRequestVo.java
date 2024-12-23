package lookids.mono.user.agreement.vo.in;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class AgreementRequestVo {
	private String uuid;
	private String policyCode;
	private Boolean agree;
	private String type;
}
