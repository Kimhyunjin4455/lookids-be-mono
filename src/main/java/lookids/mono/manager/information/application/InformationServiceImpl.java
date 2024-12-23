package lookids.manager.information.application;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lookids.manager.common.entity.BaseResponseStatus;
import lookids.manager.common.exception.BaseException;
import lookids.manager.information.domain.Information;
import lookids.manager.information.dto.in.InformationRequestDto;
import lookids.manager.information.dto.in.InformationUpdateRequestDto;
import lookids.manager.information.dto.out.InformationResponseDto;
import lookids.manager.information.infrastructure.InformationRepository;

@Service
@RequiredArgsConstructor
public class InformationServiceImpl implements InformationService {

	private final InformationRepository informationRepository;

	@Override
	public void createInformation(InformationRequestDto informationRequestDto) {
		String feedCode;
		feedCode = UUID.randomUUID().toString();
		informationRepository.save(informationRequestDto.toEntity(feedCode));
	}

	@Override
	public InformationResponseDto readInformation(String feedCode) {
		return InformationResponseDto.toDto(informationRepository.findByFeedCode(feedCode)
				.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_FEED)));
	}

	@Override
	public List<InformationResponseDto> readInformationList(String managerUuid) {
		List<Information> InformationList = informationRepository.findByManagerUuid(managerUuid);
		return InformationList.stream().map(InformationResponseDto::toDto).toList();
	}

	@Override
	public void updateInformation(String feedCode, InformationUpdateRequestDto informationUpdateRequestDto) {
		Information information = informationRepository.findByFeedCode(feedCode)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_FEED));
		information.update(informationUpdateRequestDto);
		informationRepository.save(information);
	}

	@Transactional
	@Override
	public void deleteInformation(String feedCode) {
		Information information = informationRepository.findByFeedCode(feedCode)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_FEED));
		informationRepository.deleteByFeedCode(feedCode);
	}
}
