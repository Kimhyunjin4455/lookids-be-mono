package lookids.mono.user.tier.domain;

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
public class Tier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Comment("티어 코드")
	@Column(nullable = false)
	private String tierCode;

	@Comment("티어 이름")
	@Column(nullable = false)
	private String name;

	@Comment("요구 점수")
	@Column(nullable = false)
	private Integer amount;

	@Comment("아이콘")
	@Column(nullable = false)
	private String icon;

	@Builder
	public Tier(Long id, String tierCode, String name, Integer amount, String icon) {
		this.id = id;
		this.tierCode = tierCode;
		this.name = name;
		this.amount = amount;
		this.icon = icon;
	}
}
