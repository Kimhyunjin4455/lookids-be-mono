package lookids.mono.media.application;

import java.util.List;

import lookids.mono.media.dto.in.MediaDeleteDto;
import lookids.mono.media.dto.in.MediaRequestDto;
import lookids.mono.media.dto.in.MediaUpdateDto;
import lookids.mono.media.dto.out.MediaListResponseDto;
import lookids.mono.media.dto.out.MediaResponseDto;

public interface MediaService {
	void createMedia(MediaRequestDto mediaRequestDto);

	MediaListResponseDto createMediaList(List<MediaRequestDto> mediaRequestDtoList);

	void updateMedia(MediaUpdateDto mediaUpdateDto);

	void deleteMedia(MediaDeleteDto mediaDeleteDto);

	MediaResponseDto readMedia(String mediaCode);

	List<MediaResponseDto> readMediaList(String userUuid);
}
