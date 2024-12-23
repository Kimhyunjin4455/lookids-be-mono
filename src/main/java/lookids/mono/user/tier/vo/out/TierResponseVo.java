package lookids.mono.user.tier.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class TierResponseVo {
	private String tierCode;
	private String name;
	private Integer amount;
	private String icon;
}
