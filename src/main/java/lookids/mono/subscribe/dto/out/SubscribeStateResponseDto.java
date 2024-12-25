package lookids.mono.subscribe.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.subscribe.vo.out.SubscribeStateResponseVo;

@Getter
@NoArgsConstructor
public class SubscribeStateResponseDto {
	private boolean isSubscribed;

	@Builder
	public SubscribeStateResponseDto(boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}

	public static SubscribeStateResponseDto toDto(boolean isSubscribed) {
		return SubscribeStateResponseDto.builder().isSubscribed(isSubscribed).build();
	}

	public SubscribeStateResponseVo toVo() {
		return SubscribeStateResponseVo.builder().isSubscribed(isSubscribed).build();
	}
}
