package lookids.mono.map.dto.in;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lookids.mono.map.domain.Map;
import lookids.mono.map.vo.in.PinUpdateVo;

@Getter
public class PinUpdateDto {

	private String pinCode;
	private String category;
	private Integer locationScore;

	@Builder
	public PinUpdateDto(String pinCode, String category, Integer locationScore) {
		this.pinCode = pinCode;
		this.category = category;
		this.locationScore = locationScore;
	}

	public static PinUpdateDto toDto(PinUpdateVo pinUpdateVo) {
		return PinUpdateDto.builder()
			.pinCode(pinUpdateVo.getPinCode())
			.locationScore(pinUpdateVo.getLocationScore())
			.category(pinUpdateVo.getCategory())
			.build();
	}

	public Map toUpdateEntity(Map map) {
		return Map.builder()
			.id(map.getId())
			.createdAt(map.getCreatedAt())
			.uuid(map.getUuid())
			.latitude(map.getLatitude())
			.longitude(map.getLongitude())
			.stateChecked(map.getStateChecked())
			.pinCode(map.getPinCode())
			.category(this.category)
			.locationScore(this.locationScore)
			.modDate(LocalDateTime.now())
			.build();
	}

	public static Map toDeleteEntity(Map map) {
		return Map.builder()
			.id(map.getId())
			.createdAt(map.getCreatedAt())
			.uuid(map.getUuid())
			.latitude(map.getLatitude())
			.longitude(map.getLongitude())
			.stateChecked(false)
			.pinCode(map.getPinCode())
			.category(map.getCategory())
			.locationScore(map.getLocationScore())
			.modDate(LocalDateTime.now())
			.build();
	}

}
