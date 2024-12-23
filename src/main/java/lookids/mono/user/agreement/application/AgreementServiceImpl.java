package lookids.mono.user.agreement.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.common.entity.BaseResponseStatus;
import lookids.mono.common.exception.BaseException;
import lookids.mono.user.agreement.domain.Agreement;
import lookids.mono.user.agreement.dto.in.AgreementRequestDto;
import lookids.mono.user.agreement.dto.in.AgreementUpdateDto;
import lookids.mono.user.agreement.dto.out.AgreementResponseDto;
import lookids.mono.user.agreement.infrastructure.AgreementRepository;

@Service
@AllArgsConstructor
@Slf4j
public class AgreementServiceImpl implements AgreementService {

	private final AgreementRepository agreementRepository;

	@Override
	public void createAgreement(AgreementRequestDto agreementRequestDto) {
		agreementRepository.save(agreementRequestDto.toEntity());
	}

	@Override
	public void updateAgreement(AgreementUpdateDto agreementUpdateDto) {
		Agreement agreement = agreementRepository.findByUserUuidAndPolicyCode(agreementUpdateDto.getUserUuid(),
			agreementUpdateDto.getPolicyCode()).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		agreementRepository.save(agreementUpdateDto.toEntity(agreement));
	}

	@Override
	public void deleteAgreement(String userUuid, String policyCode) {
		Agreement agreement = agreementRepository.findByUserUuidAndPolicyCode(userUuid, policyCode)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		agreementRepository.deleteById(agreement.getId());
	}

	@Transactional
	@Override
	public void updatePolicy(AgreementRequestDto agreementRequestDto) {
		Agreement agreement = agreementRepository.findByUserUuidAndTypeAndActivated(agreementRequestDto.getUserUuid(),
			agreementRequestDto.getType(), true).orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));

		agreementRepository.save(agreementRequestDto.toUpdate(agreement, false));
		agreementRepository.save(agreementRequestDto.toEntity());
	}

	@Override
	public AgreementResponseDto readAgreement(String userUuid, String policyCode) {
		return AgreementResponseDto.toDto(agreementRepository.findByUserUuidAndPolicyCode(userUuid, policyCode)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA)));
	}

	@Override
	public List<AgreementResponseDto> readAgreementList(String userUuid) {
		List<Agreement> agreementList = agreementRepository.findByUserUuidAndActivated(userUuid, true);
		return agreementList.stream().map(AgreementResponseDto::toDto).toList();
	}
}
