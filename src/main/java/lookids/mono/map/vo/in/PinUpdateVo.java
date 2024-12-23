package lookids.map.map.vo.in;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PinUpdateVo {

	private String pinCode;
	private String category;
	private Integer locationScore;

}
