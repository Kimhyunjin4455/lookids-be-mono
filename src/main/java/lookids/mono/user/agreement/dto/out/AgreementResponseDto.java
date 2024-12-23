package lookids.mono.user.agreement.dto.out;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.user.agreement.domain.Agreement;
import lookids.mono.user.agreement.vo.out.AgreementResponseVo;

@Getter
@NoArgsConstructor
public class AgreementResponseDto {

	private String userUuid;
	private String policyCode;
	private Boolean agree;
	private LocalDateTime agreedAt;

	@Builder
	public AgreementResponseDto(String userUuid, String policyCode, Boolean agree, LocalDateTime agreedAt) {
		this.userUuid = userUuid;
		this.policyCode = policyCode;
		this.agree = agree;
		this.agreedAt = agreedAt;
	}

	public static AgreementResponseDto toDto(Agreement agreement) {
		return AgreementResponseDto.builder()
			.userUuid(agreement.getUserUuid())
			.policyCode(agreement.getPolicyCode())
			.agree(agreement.getAgree())
			.agreedAt(agreement.getAgreedAt())
			.build();
	}

	public AgreementResponseVo toVo() {
		return AgreementResponseVo.builder()
			.userUuid(userUuid)
			.policyCode(policyCode)
			.agree(agree)
			.agreedAt(agreedAt)
			.build();
	}
}
