package lookids.mono.user.agreement.application;

import java.util.List;

import lookids.mono.user.agreement.dto.in.AgreementRequestDto;
import lookids.mono.user.agreement.dto.in.AgreementUpdateDto;
import lookids.mono.user.agreement.dto.out.AgreementResponseDto;

public interface AgreementService {
	void createAgreement(AgreementRequestDto agreementRequestDto);

	void updateAgreement(AgreementUpdateDto agreementUpdateDto);

	void deleteAgreement(String userUuid, String policyCode);

	void updatePolicy(AgreementRequestDto agreementRequestDto);

	AgreementResponseDto readAgreement(String userUuid, String policyCode);

	List<AgreementResponseDto> readAgreementList(String userUuid);

}
