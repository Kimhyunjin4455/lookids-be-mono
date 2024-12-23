package lookids.auth.auth.domain;

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
public class OAuth {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String uuid;

	@Column(nullable = false)
	private String provider;

	@Column(nullable = false)
	private String providerAccountId;

	@Column(nullable = false)
	private boolean isState;

	@Column
	private LocalDateTime deletedAt;


	@Builder
	public OAuth(
		Long id,
		String uuid,
		String provider,
		String providerAccountId,
		boolean isState,
		LocalDateTime deletedAt
	) {
		this.id = id;
		this.uuid = uuid;
		this.provider = provider;
		this.providerAccountId = providerAccountId;
		this.isState = isState;
		this.deletedAt = deletedAt;

	}
}
