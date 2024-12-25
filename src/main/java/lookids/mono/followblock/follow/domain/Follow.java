package lookids.mono.followblock.follow.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Follow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String followerUuid;

	@Column(nullable = false)
	private String followingUuid;

	@Column(nullable = true)
	private LocalDateTime createdAt;

	@Builder
	public Follow(Long id, String followerUuid, String followingUuid, LocalDateTime createdAt) {
		this.id = id;
		this.followerUuid = followerUuid;
		this.followingUuid = followingUuid;
		this.createdAt = createdAt;
	}

}
