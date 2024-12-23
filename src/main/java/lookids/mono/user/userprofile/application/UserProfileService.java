package lookids.user.userprofile.application;

import lookids.user.userprofile.dto.in.UserProfileImgDto;
import lookids.user.userprofile.dto.in.UserProfileNicknameDto;
import lookids.user.userprofile.dto.in.UserProfileRequestDto;
import lookids.user.userprofile.dto.in.UserProfileTierDto;
import lookids.user.userprofile.dto.in.UserProfileUpdateDto;
import lookids.user.userprofile.dto.out.UserProfileResponseDto;

public interface UserProfileService {
	void createUserProfile(UserProfileRequestDto userProfileRequestDto);

	void updateUserProfile(UserProfileUpdateDto userProfileUpdateDto);

	void updateUserProfileImage(UserProfileImgDto userProfileImgDto);

	void updateUserProfileTier(UserProfileTierDto userProfileTierDto);

	void updateUserProfileNickname(UserProfileNicknameDto userProfileNicknameDto);

	void deleteUserProfile(String userUuid);

	UserProfileResponseDto readUserProfile(String userUuid);

	UserProfileResponseDto readUserProfileWithTag(String nickname, String tag);
}
