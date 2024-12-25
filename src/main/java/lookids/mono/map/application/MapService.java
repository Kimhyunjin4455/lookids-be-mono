package lookids.mono.map.application;

import java.util.List;

import org.springframework.stereotype.Service;

import lookids.mono.map.dto.in.PinReadDto;
import lookids.mono.map.dto.in.PinRequestDto;
import lookids.mono.map.dto.in.PinUpdateDto;
import lookids.mono.map.dto.out.DetailPinResponseDto;
import lookids.mono.map.dto.out.PinResponseDto;

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
