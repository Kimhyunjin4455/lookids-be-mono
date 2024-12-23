package lookids.mono.user.point.domain;

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
public class Point {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Comment("유저 uuid")
	@Column(nullable = false)
	private String userUuid;

	@Comment("이벤트 코드")
	@Column(nullable = false)
	private String eventCode;

	@Comment("적립일")
	@Column(nullable = false)
	private LocalDateTime agreedAt;

	@Comment("타입(양수, 음수)")
	@Column(nullable = false)
	private Boolean type;

	@Comment("액수")
	@Column(nullable = false)
	private Integer value;

	@Builder
	public Point(Long id, String userUuid, String eventCode, Boolean type, Integer value) {
		this.id = id;
		this.userUuid = userUuid;
		this.eventCode = eventCode;
		this.type = type;
		this.value = value;
		this.agreedAt = LocalDateTime.now();
	}

}
