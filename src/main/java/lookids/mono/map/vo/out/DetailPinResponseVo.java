package lookids.mono.map.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class DetailPinResponseVo {

	private String mediaUrl;
	private Double latitude;
	private Double longitude;

	@Builder
	public DetailPinResponseVo(String mediaUrl, Double latitude, Double longitude) {
		this.mediaUrl = mediaUrl;
		this.latitude = latitude;
		this.longitude = longitude;
	}
}
