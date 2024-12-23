package lookids.map.map.vo.in;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PinRequestVo {

	private Double latitude;
	private Double longitude;
	private String category;
	private Integer locationScore;
	private String mediaUrl;

}
