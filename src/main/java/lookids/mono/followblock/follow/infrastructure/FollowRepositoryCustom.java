package lookids.mono.followblock.follow.infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lookids.mono.followblock.follow.dto.out.FollowResponseDto;

public interface FollowRepositoryCustom {

	Page<FollowResponseDto> findByFollowerUuid(String followerUuid, Pageable pageable);

	Page<FollowResponseDto> findByFollowingUuid(String followingUuid, Pageable pageable);

}
