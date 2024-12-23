package lookids.media.media.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@DynamicUpdate  //특정 칼럼만 update하기 위해 사용
@Entity
public class Media {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Comment("유저 uuid")
	@Column(nullable = false)
	private String userUuid;

	@Comment("미디어 코드")
	@Column(nullable = false)
	private String mediaCode;

	@Comment("미디어 타입")
	@Column(nullable = false)
	private String mediaType;

	@Comment("s3 url")
	@Column(nullable = false)
	private String mediaUrl;

	@Comment("위도")
	@Column(nullable = true)
	private Double latitude;

	@Comment("경도")
	@Column(nullable = true)
	private Double longitude;

	@Comment("상태")
	@Column(nullable = false)
	private Boolean state;

	@Comment("생성일")
	@Column(nullable = false)
	private LocalDateTime createAt;

	@Builder
	public Media(Long id, String userUuid, String mediaCode, String mediaType, String mediaUrl, Double latitude,
		Double longitude, Boolean state, LocalDateTime createAt) {
		this.id = id;
		this.userUuid = userUuid;
		this.mediaCode = mediaCode;
		this.mediaType = mediaType;
		this.mediaUrl = mediaUrl;
		this.latitude = latitude;
		this.longitude = longitude;
		this.state = state;
		this.createAt = createAt;
	}
}
