package lookids.mono.followblock.follow.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lookids.mono.followblock.follow.dto.in.FollowRequestDto;
import lookids.mono.followblock.follow.dto.out.FollowInfoResponseDto;
import lookids.mono.followblock.follow.dto.out.FollowResponseDto;

public interface FollowService {
	Boolean createFollow(FollowRequestDto followRequestDto);

	Boolean readFollow(String followerUuid, String followingUuid);

	Page<FollowResponseDto> readFollowerList(String userUuid, Pageable pageable);

	Page<FollowResponseDto> readFollowingList(String userUuid, Pageable pageable);

	Page<FollowInfoResponseDto> readFollowerUserProfile(String userUuid, Pageable pageable);

	Page<FollowInfoResponseDto> readFollowingUserProfile(String userUuid, Pageable pageable);
}
