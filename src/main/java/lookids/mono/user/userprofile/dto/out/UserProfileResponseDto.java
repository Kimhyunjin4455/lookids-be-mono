package lookids.mono.user.userprofile.dto.out;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.user.userprofile.domain.UserProfile;
import lookids.mono.user.userprofile.vo.out.UserProfileResponseVo;

@Getter
@NoArgsConstructor
public class UserProfileResponseDto {
	private String uuid;
	private String nickname;
	private String tag;
	private String tierCode;
	private LocalDate birthDate;
	private String comment;
	private String gender;
	private String image;

	@Builder
	public UserProfileResponseDto(String uuid, String nickname, String tag, String tierCode, LocalDate birthDate,
		String gender, String comment, String image) {
		this.uuid = uuid;
		this.nickname = nickname;
		this.tag = tag;
		this.tierCode = tierCode;
		this.birthDate = birthDate;
		this.gender = gender;
		this.comment = comment;
		this.image = image;
	}

	public static UserProfileResponseDto toDto(UserProfile userProfile) {
		return UserProfileResponseDto.builder()
			.uuid(userProfile.getUserUuid())
			.nickname(userProfile.getNickname())
			.tag(userProfile.getTag())
			.tierCode(userProfile.getTierCode())
			.birthDate(userProfile.getBirthDate())
			.gender(userProfile.getGender())
			.comment(userProfile.getComment())
			.image(userProfile.getImage())
			.build();
	}

	public UserProfileResponseVo toVo() {
		return UserProfileResponseVo.builder()
			.uuid(uuid)
			.nickname(nickname)
			.tag(tag)
			.tierCode(tierCode)
			.birthDate(birthDate)
			.gender(gender)
			.comment(comment)
			.image(image)
			.build();
	}
}
