package lookids.mono.user.userprofile.application;

import lookids.mono.user.userprofile.dto.in.UserProfileImgDto;
import lookids.mono.user.userprofile.dto.in.UserProfileNicknameDto;
import lookids.mono.user.userprofile.dto.in.UserProfileRequestDto;
import lookids.mono.user.userprofile.dto.in.UserProfileTierDto;
import lookids.mono.user.userprofile.dto.in.UserProfileUpdateDto;
import lookids.mono.user.userprofile.dto.out.UserProfileResponseDto;

public interface UserProfileService {
	void createUserProfile(UserProfileRequestDto userProfileRequestDto);

	void createUserProfileService(String uuid, String nickname);

	void updateUserProfile(UserProfileUpdateDto userProfileUpdateDto);

	void updateUserProfileImage(UserProfileImgDto userProfileImgDto);

	void updateUserProfileTier(UserProfileTierDto userProfileTierDto);

	void updateUserProfileNickname(UserProfileNicknameDto userProfileNicknameDto);

	void deleteUserProfile(String userUuid);

	UserProfileResponseDto readUserProfile(String userUuid);

	UserProfileResponseDto readUserProfileWithTag(String nickname, String tag);
}
