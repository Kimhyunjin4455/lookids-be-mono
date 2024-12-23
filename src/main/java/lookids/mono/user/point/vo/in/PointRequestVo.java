package lookids.mono.user.point.vo.in;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PointRequestVo {
	private String userUuid;
	private String eventCode;
	private Boolean type;
	private Integer value;
}
