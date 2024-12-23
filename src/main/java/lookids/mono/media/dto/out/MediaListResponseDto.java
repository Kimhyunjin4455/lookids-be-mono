package lookids.mono.media.dto.out;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.media.domain.Media;
import lookids.mono.media.vo.out.MediaListResponseVo;

@Getter
@NoArgsConstructor
public class MediaListResponseDto {
	private List<String> mediaCode;

	@Builder
	public MediaListResponseDto(List<String> mediaCode) {
		this.mediaCode = mediaCode;
	}

	public static MediaListResponseDto toDto(List<Media> mediaList) {
		return MediaListResponseDto.builder()
			.mediaCode(mediaList.stream().map(Media::getMediaCode) // Media 객체에서 mediaCode 추출
				.toList())
			.build();
	}

	public MediaListResponseVo toVo() {
		return MediaListResponseVo.builder().mediaCode(mediaCode).build();
	}
}
