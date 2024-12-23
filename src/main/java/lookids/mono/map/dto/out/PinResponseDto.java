package lookids.map.map.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lookids.map.map.domain.Map;
import lookids.map.map.vo.out.PinResponseVo;

@Getter
@ToString
public class PinResponseDto {

	private String pinCode;
	private Double latitude;
	private Double longitude;
	private String feedCode;
	private String mediaUrl;

	@Builder
	public PinResponseDto(String pinCode, Double latitude, Double longitude, String feedCode, String mediaUrl) {
		this.pinCode = pinCode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.feedCode = feedCode;
		this.mediaUrl = mediaUrl;
	}

	public static PinResponseDto toDto(Map map) {
		return PinResponseDto.builder()
			.pinCode(map.getPinCode())
			.latitude(map.getLatitude())
			.longitude(map.getLongitude())
			.feedCode(map.getFeedCode())
			.mediaUrl(map.getMediaUrl())
			.build();
	}

	public PinResponseVo toVo() {
		return PinResponseVo.builder()
			.pinCode(pinCode)
			.latitude(latitude)
			.longitude(longitude)
			.feedCode(feedCode)
			.mediaUrl(mediaUrl)
			.build();
	}

}
