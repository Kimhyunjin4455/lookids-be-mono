package lookids.media.media.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.media.media.domain.Media;
import lookids.media.media.vo.in.MediaUpdateVo;

@Getter
@NoArgsConstructor
public class MediaUpdateDto {
	private String userUuid;
	private String mediaCode;
	private Double latitude;
	private Double longitude;

	@Builder
	public MediaUpdateDto(String userUuid, String mediaCode, Double latitude, Double longitude) {
		this.userUuid = userUuid;
		this.mediaCode = mediaCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public static MediaUpdateDto toDto(MediaUpdateVo mediaUpdateVo, String userUuid) {
		return MediaUpdateDto.builder()
			.userUuid(userUuid)
			.mediaCode(mediaUpdateVo.getMediaCode())
			.latitude(mediaUpdateVo.getLatitude())
			.longitude(mediaUpdateVo.getLongitude())
			.build();
	}

	public Media toEntity(Media media) {
		return Media.builder()
			.id(media.getId())
			.userUuid(media.getUserUuid())
			.mediaCode(media.getMediaCode())
			.mediaType(media.getMediaType())
			.mediaUrl(media.getMediaUrl())
			.latitude(latitude)
			.longitude(longitude)
			.state(media.getState())
			.createAt(media.getCreateAt())
			.build();
	}
}
