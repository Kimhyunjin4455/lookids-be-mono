package lookids.mono.user.userprofile.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.user.userprofile.domain.UserProfile;
import lookids.mono.user.userprofile.vo.out.NicknameKafkaVo;
import lookids.mono.user.userprofile.vo.out.ProfileImageKafkaVo;
import lookids.mono.user.userprofile.vo.out.UserProfileKafkaVo;

@Getter
@NoArgsConstructor
public class UserProfileKafkaDto {
	private String userUuid;
	private String nickname;
	private String tag;
	private String image;

	@Builder
	public UserProfileKafkaDto(String userUuid, String nickname, String tag, String image) {
		this.userUuid = userUuid;
		this.nickname = nickname;
		this.tag = tag;
		this.image = image;
	}

	public static UserProfileKafkaDto toDto(UserProfile userProfile) {
		return UserProfileKafkaDto.builder()
			.userUuid(userProfile.getUserUuid())
			.nickname(userProfile.getNickname())
			.tag(userProfile.getTag())
			.image(userProfile.getImage())
			.build();
	}

	public UserProfileKafkaVo toVo() {
		return UserProfileKafkaVo.builder().uuid(userUuid).tag(tag).nickname(nickname).image(image).build();
	}

	public NicknameKafkaVo toNicknameVo() {
		return NicknameKafkaVo.builder().uuid(userUuid).tag(tag).nickname(nickname).build();
	}

	public ProfileImageKafkaVo toImageVo() {
		return ProfileImageKafkaVo.builder().uuid(userUuid).image(image).build();
	}
}
