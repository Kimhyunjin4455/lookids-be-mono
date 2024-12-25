package lookids.mono.commentread.application.port.in;

import lookids.mono.commentread.application.port.dto.UserProfileImageDto;
import lookids.mono.commentread.application.port.dto.UserProfileNicknameDto;

public interface UserProfileUpdateUseCase {

	void updateNickname(UserProfileNicknameDto userProfileNicknameDto);

	void updateProfileImage(UserProfileImageDto userProfileImageDto);
}
