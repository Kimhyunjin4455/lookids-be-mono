package lookids.batch.follow.adaptor.in.web.mapper;

import org.springframework.stereotype.Component;

import lookids.batch.follow.adaptor.in.web.out.FollowCountResponseVo;
import lookids.batch.follow.application.port.dto.FollowCountResponseDto;

@Component
public class FollowWebVoMapper {

	public FollowCountResponseVo toFollowCountResponseVo(FollowCountResponseDto followCountResponseDto) {
		return FollowCountResponseVo.builder()
			.followerCount(followCountResponseDto.getFollowerCount())
			.followingCount(followCountResponseDto.getFollowingCount())
			.build();
	}
}
