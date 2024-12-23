package Lookids.follow.infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import Lookids.follow.dto.out.FollowInfoResponseDto;

public interface FollowInfoRepositoryCustom {

	Page<FollowInfoResponseDto> findByReceiverUuid(String followerUuid, Pageable pageable);
	Page<FollowInfoResponseDto> findBySenderUuid(String followingUuid, Pageable pageable);

}
