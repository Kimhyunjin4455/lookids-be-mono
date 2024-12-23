package lookids.manager.policy.domain;

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
public class Policy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String policyCode;

	@Column(nullable = false)
	private String policyName;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@Builder
	public Policy(Long id, String policyCode, String policyName, String content, LocalDateTime createdAt) {
		this.id = id;
		this.policyCode = policyCode;
		this.policyName = policyName;
		this.content = content;
		this.createdAt = createdAt;
	}

}
