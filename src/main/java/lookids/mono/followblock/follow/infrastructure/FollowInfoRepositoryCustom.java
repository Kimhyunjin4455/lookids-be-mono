package lookids.mono.followblock.follow.infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lookids.mono.followblock.follow.dto.out.FollowInfoResponseDto;

public interface FollowInfoRepositoryCustom {

	Page<FollowInfoResponseDto> findByReceiverUuid(String followerUuid, Pageable pageable);

	Page<FollowInfoResponseDto> findBySenderUuid(String followingUuid, Pageable pageable);

}
