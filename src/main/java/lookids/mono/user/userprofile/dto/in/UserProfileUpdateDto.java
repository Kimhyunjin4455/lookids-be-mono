package lookids.mono.user.userprofile.dto.in;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.user.userprofile.domain.UserProfile;
import lookids.mono.user.userprofile.vo.in.UserProfileUpdateVo;

@Getter
@NoArgsConstructor
public class UserProfileUpdateDto {

	private String userUuid;
	// private LocalDate birthDate;
	// private String gender;
	private String comment;

	@Builder
	public UserProfileUpdateDto(String userUuid, String comment, LocalDate birthDate, String gender) {
		this.userUuid = userUuid;
		this.comment = comment;
		// this.birthDate = birthDate;
		// this.gender = gender;
	}

	public static UserProfileUpdateDto toDto(UserProfileUpdateVo userProfileUpdateVo, String userUuid) {
		return UserProfileUpdateDto.builder().userUuid(userUuid)
			// .birthDate(userProfileUpdateVo.getBirthDate())
			// .gender(userProfileUpdateVo.getGender())
			.comment(userProfileUpdateVo.getComment()).build();
	}

	public UserProfile toUpdate(UserProfile userProfile) {
		return UserProfile.builder()
			.id(userProfile.getId())
			.userUuid(userProfile.getUserUuid())
			.nickname(userProfile.getNickname())
			.tag(userProfile.getTag())
			// .birthDate(birthDate)
			// .gender(gender)
			.birthDate(userProfile.getBirthDate())
			.gender(userProfile.getGender())
			.comment(comment)
			.tierCode(userProfile.getTierCode())
			.image(userProfile.getImage())
			.build();
	}
}
