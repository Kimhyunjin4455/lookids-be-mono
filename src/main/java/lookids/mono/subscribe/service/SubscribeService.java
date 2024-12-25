package lookids.mono.subscribe.service;

import lookids.mono.subscribe.dto.in.SubscribeRequestDto;
import lookids.mono.subscribe.dto.out.SubscribeResponseDto;
import lookids.mono.subscribe.dto.out.SubscribeStateResponseDto;

public interface SubscribeService {
	SubscribeResponseDto readSubscribers(String authorUuid);

	void createSubscribe(SubscribeRequestDto subscribeRequestDto);

	void deleteSubscribe(SubscribeRequestDto subscribeRequestDto);

	SubscribeStateResponseDto existsByAuthorUuidAndSubscriberUuid(String authorUuid, String subscriberUuid);

}
