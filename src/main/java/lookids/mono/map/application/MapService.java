package lookids.map.map.application;

import java.util.List;

import org.springframework.stereotype.Service;

import lookids.map.map.dto.in.PinReadDto;
import lookids.map.map.dto.in.PinRequestDto;
import lookids.map.map.dto.in.PinUpdateDto;
import lookids.map.map.dto.out.DetailPinResponseDto;
import lookids.map.map.dto.out.PinResponseDto;

@Service
public interface MapService {

	void createPin(PinRequestDto pinRequestDto);
	void createBasicPin(PinRequestDto pinRequestDto);
	List<PinResponseDto> readPin(String uuid, PinReadDto pinReadDto);
	List<PinResponseDto> readBasicPin(String category, PinReadDto pinReadDto);
	DetailPinResponseDto readDetailPin(String pinCode);
	void updatePin(PinUpdateDto pinUpdateDto);
	void deletePin(String pinCode);

}
