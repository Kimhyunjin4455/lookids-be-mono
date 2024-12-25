package lookids.mono.followblock.block.domain;

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
public class Block {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String blockedUuid;

	@Column(nullable = false)
	private String uuid;

	@Column(nullable = false)
	private boolean state;

	@Builder
	public Block(Long id, String blockedUuid, String uuid, boolean state) {
		this.id = id;
		this.blockedUuid = blockedUuid;
		this.uuid = uuid;
		this.state = state;
	}

}
