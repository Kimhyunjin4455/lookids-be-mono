package lookids.user.userprofile.dto.in;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.user.userprofile.domain.UserProfile;
import lookids.user.userprofile.vo.in.UserProfileNicknameVo;

@Getter
@NoArgsConstructor
public class UserProfileNicknameDto {

	private String userUuid;
	private String nickname;
	private LocalDate birthDate;
	private String gender;

	@Builder
	public UserProfileNicknameDto(String userUuid, String nickname, LocalDate birthDate, String gender) {
		this.userUuid = userUuid;
		this.nickname = nickname;
		this.birthDate = birthDate;
		this.gender = gender;
	}

	public static UserProfileNicknameDto toDto(UserProfileNicknameVo userProfileNicknameVo, String userUuid) {
		return UserProfileNicknameDto.builder()
			.userUuid(userUuid)
			.nickname(userProfileNicknameVo.getNickname())
			.birthDate(userProfileNicknameVo.getBirthDate())
			.gender(userProfileNicknameVo.getGender())
			.build();
	}

	public UserProfile toEntity(UserProfile userProfile, String tag) {
		return UserProfile.builder()
			.id(userProfile.getId())
			.userUuid(userProfile.getUserUuid())
			.nickname(nickname)
			.tag(tag)
			.birthDate(birthDate)
			.gender(gender)
			.comment(userProfile.getComment())
			.tierCode(userProfile.getTierCode())
			.image(userProfile.getImage())
			.build();
	}
}
