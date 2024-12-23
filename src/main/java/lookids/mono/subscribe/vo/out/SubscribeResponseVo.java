package lookids.subscribe.subscribe.vo.out;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.subscribe.subscribe.dto.out.SubscribeResponseDto;

@Getter
@NoArgsConstructor
public class SubscribeResponseVo {
	private String authorUuid;
	private List<String> subscriberUuids;

	@Builder
	public SubscribeResponseVo(
		String authorUuid,
		List<String> subscriberUuids
	) {
		this.authorUuid = authorUuid;
		this.subscriberUuids = subscriberUuids;
	}

	public static SubscribeResponseVo toVo(SubscribeResponseDto subscribeResponseDto){
		return SubscribeResponseVo.builder()
			.authorUuid(subscribeResponseDto.getAuthorUuid())
			.subscriberUuids(subscribeResponseDto.getSubscriberUuids())
			.build();
	}

}
