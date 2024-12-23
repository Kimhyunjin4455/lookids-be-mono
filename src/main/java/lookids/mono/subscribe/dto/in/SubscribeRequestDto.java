package lookids.subscribe.subscribe.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.subscribe.subscribe.domain.Subscribe;
import lookids.subscribe.subscribe.vo.in.SubscribeRequestVo;

@Getter
@NoArgsConstructor
public class SubscribeRequestDto {
	private String subscriberUuid;
	private String authorUuid;

	@Builder
	public SubscribeRequestDto(
		String subscriberUuid,
		String authorUuid
	) {
		this.subscriberUuid = subscriberUuid;
		this.authorUuid = authorUuid;
	}

	public static SubscribeRequestDto toDto(String subscriberUuid, SubscribeRequestVo subscribeRequestVo){
		return SubscribeRequestDto.builder()
			.subscriberUuid(subscriberUuid)
			.authorUuid(subscribeRequestVo.getAuthorUuid())
			.build();
	}

	public Subscribe toEntity(){
		return Subscribe.builder()
			.subscriberUuid(subscriberUuid)
			.authorUuid(authorUuid)
			.build();
	}
}
