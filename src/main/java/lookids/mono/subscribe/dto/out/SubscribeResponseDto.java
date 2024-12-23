package lookids.subscribe.subscribe.dto.out;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.subscribe.subscribe.vo.out.SubscribeResponseVo;

@Getter
@NoArgsConstructor
public class SubscribeResponseDto {
	private String authorUuid;
	private List<String> subscriberUuids;

	@Builder
	public SubscribeResponseDto(
		String authorUuid,
		List<String> subscriberUuids
	) {
		this.authorUuid = authorUuid;
		this.subscriberUuids = subscriberUuids;
	}

	public static SubscribeResponseDto toDto(String authorUuid, List<String> subscriberUuids){
		return SubscribeResponseDto.builder()
			.authorUuid(authorUuid)
			.subscriberUuids(subscriberUuids)
			.build();
	}

	public SubscribeResponseVo toVo(){
		return SubscribeResponseVo.builder()
			.authorUuid(authorUuid)
			.subscriberUuids(subscriberUuids)
			.build();
	}
}
