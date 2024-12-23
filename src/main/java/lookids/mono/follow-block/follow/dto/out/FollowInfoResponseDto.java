package Lookids.follow.dto.out;

import Lookids.follow.vo.out.FollowUserProfileResponseVo;
import lombok.Builder;

public class FollowInfoResponseDto {

	private String uuid;
	private String nickname;
	private String tag;
	private String image;

	@Builder
	public FollowInfoResponseDto(String uuid, String nickname, String tag, String image) {
		this.uuid = uuid;
		this.nickname = nickname;
		this.tag = tag;
		this.image = image;
	}

	public FollowUserProfileResponseVo toVo() {
		return FollowUserProfileResponseVo.builder()
				.uuid(uuid)
				.nickname(nickname)
				.tag(tag)
				.image(image)
				.build();
	}

}
