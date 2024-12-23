package lookids.mono.user.userprofile.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.user.userprofile.domain.UserProfile;
import lookids.mono.user.userprofile.vo.in.UserProfileTierVo;

@Getter
@NoArgsConstructor
public class UserProfileTierDto {

	private String userUuid;
	private String tier;

	@Builder
	public UserProfileTierDto(String userUuid, String tier) {
		this.userUuid = userUuid;
		this.tier = tier;
	}

	public static UserProfileTierDto toDto(UserProfileTierVo userProfileTierVo) {
		return UserProfileTierDto.builder()
			.userUuid(userProfileTierVo.getUuid())
			.tier(userProfileTierVo.getTier())
			.build();
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
			.tierCode(tier)
			.image(userProfile.getImage())
			.build();
	}
}
