package lookids.mono.batch.follow.adaptor.out.infrastructure.entity;

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
public class FollowCountEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String uuid;

	@Column(nullable = false)
	private Integer followingCount;
	@Column(nullable = false)
	private Integer followerCount;

	@Column(nullable = false)
	private LocalDateTime updateAt;

	@Builder
	public FollowCountEntity(Long id, String uuid, Integer followingCount, Integer followerCount,
		LocalDateTime updateAt) {
		this.id = id;
		this.uuid = uuid;
		this.followingCount = followingCount;
		this.followerCount = followerCount;
		this.updateAt = updateAt;
	}

}
