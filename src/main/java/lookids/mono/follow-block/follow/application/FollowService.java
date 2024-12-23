package Lookids.follow.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import Lookids.follow.dto.in.FollowRequestDto;
import Lookids.follow.dto.out.FollowInfoResponseDto;
import Lookids.follow.dto.out.FollowResponseDto;

public interface FollowService {
	Boolean createFollow(FollowRequestDto followRequestDto);
	Boolean readFollow(String followerUuid, String followingUuid);
	Page<FollowResponseDto> readFollowerList(String userUuid, Pageable pageable);
	Page<FollowResponseDto> readFollowingList(String userUuid, Pageable pageable);
	Page<FollowInfoResponseDto> readFollowerUserProfile(String userUuid, Pageable pageable);
	Page<FollowInfoResponseDto> readFollowingUserProfile(String userUuid, Pageable pageable);
}
