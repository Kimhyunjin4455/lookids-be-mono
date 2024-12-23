package Lookids.follow.infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import Lookids.follow.dto.out.FollowResponseDto;

public interface FollowRepositoryCustom {

	Page<FollowResponseDto> findByFollowerUuid(String followerUuid, Pageable pageable);
	Page<FollowResponseDto> findByFollowingUuid(String followingUuid, Pageable pageable);

}
