package lookids.user.agreement.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.user.agreement.domain.Agreement;
import lookids.user.agreement.vo.in.AgreementUpdateVo;

@Getter
@NoArgsConstructor
public class AgreementUpdateDto {

	private String userUuid;
	private String policyCode;
	private Boolean agree;

	@Builder
	public AgreementUpdateDto(String userUuid, String policyCode, Boolean agree) {
		this.userUuid = userUuid;
		this.policyCode = policyCode;
		this.agree = agree;
	}

	public static AgreementUpdateDto toDto(AgreementUpdateVo agreementUpdateVo, String userUuid) {
		return AgreementUpdateDto.builder()
			.userUuid(userUuid)
			.policyCode(agreementUpdateVo.getPolicyCode())
			.agree(agreementUpdateVo.getAgree())
			.build();
	}

	public Agreement toEntity(Agreement agreement) {
		return Agreement.builder()
			.id(agreement.getId())
			.userUuid(agreement.getUserUuid())
			.policyCode(agreement.getPolicyCode())
			.agree(agree)
			.type(agreement.getType())
			.build();
	}
}
