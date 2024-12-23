package lookids.elasticsearch.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SearchUserResponseVo {

	private String uuid;
	private String nickname;
	private String tag;
	private String profileImage;

	@Builder
	public SearchUserResponseVo(String uuid, String nickname, String tag, String profileImage) {
		this.uuid = uuid;
		this.nickname = nickname;
		this.tag = tag;
		this.profileImage = profileImage;
	}

}
