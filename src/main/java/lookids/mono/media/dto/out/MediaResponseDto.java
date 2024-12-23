package lookids.media.media.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.media.media.domain.Media;
import lookids.media.media.vo.out.MediaResponseVo;

@Getter
@NoArgsConstructor
public class MediaResponseDto {

	private String userUuid;
	private String mediaCode;
	private String mediaType;
	private String mediaUrl;
	private Double latitude;
	private Double longitude;

	@Builder
	public MediaResponseDto(String userUuid, String mediaCode, String mediaType, String mediaUrl, Double latitude,
		Double longitude) {
		this.userUuid = userUuid;
		this.mediaCode = mediaCode;
		this.mediaType = mediaType;
		this.mediaUrl = mediaUrl;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public static MediaResponseDto toDto(Media media) {
		return MediaResponseDto.builder()
			.userUuid(media.getUserUuid())
			.mediaCode(media.getMediaCode())
			.mediaType(media.getMediaType())
			.mediaUrl(media.getMediaUrl())
			.latitude(media.getLatitude())
			.longitude(media.getLongitude())
			.build();
	}

	public MediaResponseVo toVo() {
		return MediaResponseVo.builder()
			.userUuid(userUuid)
			.mediaCode(mediaCode)
			.mediaType(mediaType)
			.mediaUrl(mediaUrl)
			.latitude(latitude)
			.longitude(longitude)
			.build();
	}
}
