package lookids.elasticsearch.dto.out;

import lombok.Builder;
import lombok.Getter;
import lookids.elasticsearch.domain.SearchUser;
import lookids.elasticsearch.vo.SearchUserResponseVo;

@Getter
public class SearchUserResponseDto {

	private String uuid;
	private String nickname;
	private String tag;
	private String profileImage;

	@Builder
	public SearchUserResponseDto(String uuid, String nickname, String tag, String profileImage) {
		this.uuid = uuid;
		this.nickname = nickname;
		this.tag = tag;
		this.profileImage = profileImage;
	}

	public static SearchUserResponseDto toDto(SearchUser searchUser) {
		return SearchUserResponseDto.builder()
			.uuid(searchUser.getUuid())
			.nickname(searchUser.getNickname())
			.tag(searchUser.getTag())
			.profileImage(searchUser.getImage())
			.build();
	}

	public SearchUserResponseVo toVo() {
		return SearchUserResponseVo.builder()
			.uuid(this.uuid)
			.nickname(this.nickname)
			.tag(this.tag)
			.profileImage(this.profileImage)
			.build();
	}

}
