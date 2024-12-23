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
public class Auth {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String uuid;

	@Column(nullable = false)
	private String loginId;

	@Column(nullable = false)
	private String userEmail;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private boolean isState;

	@Column
	private LocalDateTime deletedAt;

	@Builder
	public Auth(
		Long id,
		String uuid,
		String loginId,
		String userEmail,
		String password,
		boolean isState,
		LocalDateTime deletedAt
	) {
		this.id = id;
		this.uuid = uuid;
		this.loginId = loginId;
		this.userEmail = userEmail;
		this.password = password;
		this.isState = isState;
		this.deletedAt = deletedAt;
	}

}
