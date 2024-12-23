package lookids.user.tier.vo.in;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class TierRequestVo {
	private String tierCode;
	private String name;
	private Integer amount;
	private String icon;
}
