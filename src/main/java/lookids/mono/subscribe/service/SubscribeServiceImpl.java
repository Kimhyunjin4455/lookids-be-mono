package lookids.mono.subscribe.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lookids.mono.common.entity.BaseResponseStatus;
import lookids.mono.common.exception.BaseException;
import lookids.mono.subscribe.domain.Subscribe;
import lookids.mono.subscribe.dto.in.SubscribeRequestDto;
import lookids.mono.subscribe.dto.out.SubscribeResponseDto;
import lookids.mono.subscribe.dto.out.SubscribeStateResponseDto;
import lookids.mono.subscribe.repository.SubscribeRepository;

@Service
@RequiredArgsConstructor
public class SubscribeServiceImpl implements SubscribeService {

	private final SubscribeRepository subscribeRepository;

	@Override
	@Cacheable(value = "subscribers", key = "#authorUuid")
	public SubscribeResponseDto readSubscribers(String authorUuid) {
		List<Subscribe> subscribers = subscribeRepository.findByAuthorUuid(authorUuid);

		List<String> subscriberUuids = subscribers.stream()
			.map(Subscribe::getSubscriberUuid)
			.collect(Collectors.toList());

		return SubscribeResponseDto.toDto(authorUuid, subscriberUuids);
	}

	// 게시글 알림 신청 버튼이 off 상태일 때 적용 (등록)
	@Override
	public void createSubscribe(SubscribeRequestDto subscribeRequestDto) {
		if (subscribeRepository.findByAuthorUuidAndSubscriberUuid(subscribeRequestDto.getAuthorUuid(),
			subscribeRequestDto.getSubscriberUuid()) == null) {
			subscribeRepository.save(subscribeRequestDto.toEntity());
		} else {
			throw new BaseException(BaseResponseStatus.EXIST_NOTIFICATION_SETTING);
		}
	}

	// 게시글 알림 신청 버튼이 on 상태일 때 적용 (삭제)
	@Override
	@Transactional
	public void deleteSubscribe(SubscribeRequestDto subscribeRequestDto) {
		subscribeRepository.deleteByAuthorUuidAndSubscriberUuid(subscribeRequestDto.getAuthorUuid(),
			subscribeRequestDto.getSubscriberUuid());
	}

	@Override
	public SubscribeStateResponseDto existsByAuthorUuidAndSubscriberUuid(String authorUuid, String subscriberUuid) {
		boolean isExistSubscriber = subscribeRepository.existsByAuthorUuidAndSubscriberUuid(authorUuid, subscriberUuid);
		return SubscribeStateResponseDto.toDto(isExistSubscriber);
		//throw new IllegalArgumentException("게시글 알림 신청을 하지 않은 사용자입니다.");
		//throw new BaseException(BaseResponseStatus.NO_EXIST_NOTIFICATION_SETTING);

	}
}
