package lookids.media.media.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.media.common.entity.BaseResponse;
import lookids.media.media.application.MediaService;
import lookids.media.media.dto.out.MediaResponseDto;
import lookids.media.media.vo.out.MediaResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/read/media")
public class MediaReadController {

	private final MediaService mediaService;

	@Operation(summary = "readMediaList API", description = "readMediaList API 입니다.")
	@GetMapping("/all")
	public BaseResponse<List<MediaResponseVo>> readMediaList(@RequestParam(value = "userUuid") String userUuid) {
		List<MediaResponseDto> mediaResponseDtoList = mediaService.readMediaList(userUuid);
		return new BaseResponse<>(mediaResponseDtoList.stream().map(MediaResponseDto::toVo).toList());
	}

	@Operation(summary = "readMedia API", description = "readMedia API 입니다.")
	@GetMapping()
	public BaseResponse<MediaResponseVo> readMedia(@RequestParam(value = "mediaCode") String mediaCode) {
		return new BaseResponse<>(mediaService.readMedia(mediaCode).toVo());
	}
}
