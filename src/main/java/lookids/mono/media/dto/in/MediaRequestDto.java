package lookids.mono.media.dto.in;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.media.domain.Media;
import lookids.mono.media.vo.in.MediaRequestVo;

@Getter
@NoArgsConstructor
public class MediaRequestDto {
	private String userUuid;
	private String mediaType;
	private String mediaUrl;
	private Double latitude;
	private Double longitude;

	@Builder
	public MediaRequestDto(String userUuid, String mediaType, String mediaUrl, Double latitude, Double longitude) {
		this.userUuid = userUuid;
		this.mediaType = mediaType;
		this.mediaUrl = mediaUrl;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public static MediaRequestDto toDto(MediaRequestVo mediaRequestVo, String userUuid) {
		return MediaRequestDto.builder()
			.userUuid(userUuid)
			.mediaType(mediaRequestVo.getMediaType())
			.mediaUrl(mediaRequestVo.getMediaUrl())
			.latitude(mediaRequestVo.getLatitude())
			.longitude(mediaRequestVo.getLongitude())
			.build();
	}

	public Media toEntity() {
		return Media.builder()
			.userUuid(userUuid)
			.mediaCode(UUID.randomUUID().toString())
			.mediaType(mediaType)
			.mediaUrl(mediaUrl)
			.latitude(latitude)
			.longitude(longitude)
			.state(true)
			.createAt(LocalDateTime.now())
			.build();
	}
}
