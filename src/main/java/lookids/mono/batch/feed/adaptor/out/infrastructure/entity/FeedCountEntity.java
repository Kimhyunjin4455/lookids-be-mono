package lookids.mono.batch.feed.adaptor.out.infrastructure.entity;

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
public class FeedCountEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String uuid;

	@Column(nullable = false)
	private Integer count;

	@Column(nullable = false)
	private LocalDateTime updateAt;

	@Builder
	public FeedCountEntity(Long id, String uuid, Integer count, LocalDateTime updateAt) {
		this.id = id;
		this.uuid = uuid;
		this.count = count;
		this.updateAt = updateAt;
	}

}
