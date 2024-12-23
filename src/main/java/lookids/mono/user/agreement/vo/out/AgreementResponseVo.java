package lookids.mono.user.agreement.vo.out;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class AgreementResponseVo {

	private String userUuid;
	private String policyCode;
	private Boolean agree;
	private LocalDateTime agreedAt;
}
