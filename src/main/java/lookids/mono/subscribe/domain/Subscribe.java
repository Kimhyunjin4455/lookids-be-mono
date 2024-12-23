package lookids.subscribe.subscribe.domain;

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
public class Subscribe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String authorUuid;

	@Column(nullable = false)
	private String subscriberUuid;

	@Builder
	public Subscribe(
		String authorUuid,
		String subscriberUuid
	) {
		this.authorUuid = authorUuid;
		this.subscriberUuid = subscriberUuid;
	}


}
