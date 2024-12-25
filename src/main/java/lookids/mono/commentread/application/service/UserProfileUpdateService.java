package lookids.mono.commentread.application.service;

import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lookids.mono.commentread.application.mapper.CommentReadDtoMapper;
import lookids.mono.commentread.application.port.dto.UserProfileImageDto;
import lookids.mono.commentread.application.port.dto.UserProfileNicknameDto;
import lookids.mono.commentread.application.port.in.UserProfileUpdateUseCase;
import lookids.mono.commentread.application.port.out.CommentRepositoryPort;

@RequiredArgsConstructor
@Service
public class UserProfileUpdateService implements UserProfileUpdateUseCase {

	private final CommentRepositoryPort commentRepositoryPort;
	private final CommentReadDtoMapper commentReadDtoMapper;

	public void updateNickname(UserProfileNicknameDto userProfileNicknameDto) {
		Update update = new Update().set("nickname", userProfileNicknameDto.getNickname())
			.set("tag", userProfileNicknameDto.getTag());
		commentRepositoryPort.updateUserProfile(
			commentReadDtoMapper.toProfileUpdateSaveDto(userProfileNicknameDto.getUserUuid(), update));
	}

	public void updateProfileImage(UserProfileImageDto userProfileImageDto) {
		Update update = new Update().set("profileImg", userProfileImageDto.getImage());
		commentRepositoryPort.updateUserProfile(
			commentReadDtoMapper.toProfileUpdateSaveDto(userProfileImageDto.getUserUuid(), update));
	}
}
