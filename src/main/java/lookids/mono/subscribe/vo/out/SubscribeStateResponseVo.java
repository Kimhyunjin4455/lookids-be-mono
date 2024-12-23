package lookids.subscribe.subscribe.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SubscribeStateResponseVo {
	private boolean isSubscribed;

	@Builder
	public SubscribeStateResponseVo(boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}


}
