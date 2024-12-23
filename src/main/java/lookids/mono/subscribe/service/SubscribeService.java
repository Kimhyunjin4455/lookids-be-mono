package lookids.subscribe.subscribe.service;

import lookids.subscribe.subscribe.dto.in.SubscribeRequestDto;
import lookids.subscribe.subscribe.dto.out.SubscribeResponseDto;
import lookids.subscribe.subscribe.dto.out.SubscribeStateResponseDto;

public interface SubscribeService {
	SubscribeResponseDto readSubscribers(String authorUuid);
	void createSubscribe(SubscribeRequestDto subscribeRequestDto);
	void deleteSubscribe(SubscribeRequestDto subscribeRequestDto);
	SubscribeStateResponseDto existsByAuthorUuidAndSubscriberUuid(String authorUuid, String subscriberUuid);

}
