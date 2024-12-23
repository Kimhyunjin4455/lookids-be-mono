package lookids.manager.information.application;

import java.util.List;

import lookids.manager.information.dto.in.InformationRequestDto;
import lookids.manager.information.dto.in.InformationUpdateRequestDto;
import lookids.manager.information.dto.out.InformationResponseDto;

public interface InformationService {

	void createInformation(InformationRequestDto informationRequestDto);
	InformationResponseDto readInformation(String feedCode);
	List<InformationResponseDto> readInformationList(String managerUuid);
	void updateInformation(String feedCode, InformationUpdateRequestDto informationUpdateRequestDto);
	void deleteInformation(String feedCode);

}
