package lookids.map.map.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lookids.map.map.domain.Map;
import lookids.map.map.vo.out.DetailPinResponseVo;

@Getter
@ToString
public class DetailPinResponseDto {

	private String mediaUrl;
	private Double latitude;
	private Double longitude;

	@Builder
	public DetailPinResponseDto(String mediaUrl, Double latitude, Double longitude) {
		this.mediaUrl = mediaUrl;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public static DetailPinResponseDto toDto(Map map) {
		return DetailPinResponseDto.builder()
			.mediaUrl(map.getMediaUrl())
			.latitude(map.getLatitude())
			.longitude(map.getLongitude())
			.build();
	}

	public DetailPinResponseVo toVo(DetailPinResponseDto detailPinResponseDto) {
		return DetailPinResponseVo.builder()
			.mediaUrl(detailPinResponseDto.getMediaUrl())
			.latitude(detailPinResponseDto.getLatitude())
			.longitude(detailPinResponseDto.getLongitude())
			.build();
	}


}
