package lookids.user.agreement.domain;

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
public class Agreement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Comment("유저 uuid")
	@Column(nullable = false)
	private String userUuid;

	@Comment("정책 코드")
	@Column(nullable = false)
	private String policyCode;

	@Comment("동의 여부")
	@Column(nullable = false)
	private Boolean agree;

	@Comment("동의일자")
	@Column(nullable = false)
	private LocalDateTime agreedAt;

	@Comment("정책 타입")
	@Column(nullable = false)
	private String type;

	@Comment("정책 활성화 여부")
	@Column(nullable = false)
	private Boolean activated;

	@Builder
	public Agreement(Long id, String userUuid, String policyCode, Boolean agree, String type) {
		this.id = id;
		this.userUuid = userUuid;
		this.policyCode = policyCode;
		this.agree = agree;
		this.agreedAt = LocalDateTime.now();
		this.type = type;
		this.activated = true;
	}
}
