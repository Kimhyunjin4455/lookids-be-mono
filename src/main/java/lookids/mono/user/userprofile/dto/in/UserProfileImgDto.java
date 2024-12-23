package lookids.mono.user.userprofile.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.user.userprofile.domain.UserProfile;
import lookids.mono.user.userprofile.vo.in.UserProfileImgVo;

@Getter
@NoArgsConstructor
public class UserProfileImgDto {

	private String userUuid;
	private String image;

	@Builder
	public UserProfileImgDto(String userUuid, String image) {
		this.userUuid = userUuid;
		this.image = image;
	}

	public static UserProfileImgDto toDto(UserProfileImgVo userProfileImgVo, String userUuid) {
		return UserProfileImgDto.builder().userUuid(userUuid).image(userProfileImgVo.getImage()).build();
	}

	public UserProfile toEntity(UserProfile userProfile) {
		return UserProfile.builder()
			.id(userProfile.getId())
			.userUuid(userProfile.getUserUuid())
			.nickname(userProfile.getNickname())
			.tag(userProfile.getTag())
			.birthDate(userProfile.getBirthDate())
			.gender(userProfile.getGender())
			.comment(userProfile.getComment())
			.tierCode(userProfile.getTierCode())
			.image(image)
			.build();
	}
}
