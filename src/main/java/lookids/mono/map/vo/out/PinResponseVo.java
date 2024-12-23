package lookids.map.map.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PinResponseVo {

	private	String pinCode;
	private Double latitude;
	private Double longitude;
	private String category;
	private Integer locationScore;
	private String feedCode;
	private String mediaUrl;

	@Builder
	public PinResponseVo(
		String pinCode,
		Double latitude,
		Double longitude,
		String category,
		Integer locationScore,
		String feedCode,
		String mediaUrl
	) {
		this.pinCode = pinCode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.category = category;
		this.locationScore = locationScore;
		this.feedCode = feedCode;
		this.mediaUrl = mediaUrl;
	}

}
