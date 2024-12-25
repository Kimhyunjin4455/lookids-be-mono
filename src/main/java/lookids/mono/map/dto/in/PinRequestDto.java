package lookids.mono.map.dto.in;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lookids.mono.map.domain.Map;
import lookids.mono.map.vo.in.PinRequestVo;

@Getter
public class PinRequestDto {

	private Long id;
	private String uuid;
	private Double latitude;
	private Double longitude;
	private String category;
	private String mediaUrl;
	private Integer locationScore;
	private LocalDateTime createdAt;
	private Boolean stateChecked;

	@Builder
	public PinRequestDto(Long id, String uuid, Double latitude, Double longitude, String category, String mediaUrl,
		Integer locationScore, LocalDateTime createdAt, Boolean stateChecked) {
		this.id = id;
		this.uuid = uuid;
		this.latitude = latitude;
		this.longitude = longitude;
		this.category = category;
		this.mediaUrl = mediaUrl;
		this.locationScore = locationScore;
		this.createdAt = createdAt;
		this.stateChecked = stateChecked;
	}

	public static PinRequestDto toDto(String uuid, PinRequestVo pinRequestVo) {
		return PinRequestDto.builder()
			.uuid(uuid)
			.latitude(pinRequestVo.getLatitude())
			.longitude(pinRequestVo.getLongitude())
			.mediaUrl(pinRequestVo.getMediaUrl())
			.category(pinRequestVo.getCategory())
			.locationScore(pinRequestVo.getLocationScore())
			.build();
	}

	public Map toEntity(String feedCode) {
		return Map.builder()
			.uuid(uuid)
			.latitude(latitude)
			.longitude(longitude)
			.feedCode(feedCode)
			.category(category)
			.locationScore(locationScore)
			.mediaUrl(mediaUrl)
			.pinCode(UUID.randomUUID().toString())
			.createdAt(LocalDateTime.now())
			.stateChecked(true)
			.build();
	}

}
