package lookids.mono.map.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Map {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true)
	private String uuid;

	@Column(nullable = true)
	private String pinCode;

	@Column(nullable = true)
	private String feedCode;

	@Column(nullable = false)
	private Double latitude;

	@Column(nullable = false)
	private Double longitude;

	@Column(nullable = true)
	private String category;

	@Column(nullable = true)
	private String mediaUrl;

	@Column(nullable = true)
	private Integer locationScore;

	@Column(nullable = false)
	private Boolean stateChecked;

	@Column(nullable = true)
	private LocalDateTime createdAt;

	@Column(nullable = true)
	private LocalDateTime modDate;

	@Builder
	public Map(Long id, String uuid, String pinCode, String feedCode, Double latitude, Double longitude,
		String category, String mediaUrl, Integer locationScore, Boolean stateChecked, LocalDateTime createdAt,
		LocalDateTime modDate) {
		this.id = id;
		this.uuid = uuid;
		this.pinCode = pinCode;
		this.feedCode = feedCode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.category = category;
		this.mediaUrl = mediaUrl;
		this.locationScore = locationScore;
		this.stateChecked = stateChecked;
		this.createdAt = createdAt;
		this.modDate = modDate;
	}

}
