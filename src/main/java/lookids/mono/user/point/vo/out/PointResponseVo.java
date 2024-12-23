package lookids.user.point.vo.out;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class PointResponseVo {
	private String userUuid;
	private String eventCode;
	private LocalDateTime agreedAt;
	private Boolean type;
	private Integer value;
}
