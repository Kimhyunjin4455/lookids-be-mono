package lookids.mono.followblock.follow.domain;

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
public class FollowInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String senderUuid;
	private String senderNickname;
	private String senderTag;
	private String senderImage;
	private String receiverUuid;
	private String receiverNickname;
	private String receiverTag;
	private String receiverImage;

	@Builder
	public FollowInfo(Long id, String senderUuid, String senderNickname, String senderTag, String senderImage,
		String receiverUuid, String receiverNickname, String receiverTag, String receiverImage) {
		this.id = id;
		this.senderUuid = senderUuid;
		this.senderNickname = senderNickname;
		this.senderTag = senderTag;
		this.senderImage = senderImage;
		this.receiverUuid = receiverUuid;
		this.receiverNickname = receiverNickname;
		this.receiverTag = receiverTag;
		this.receiverImage = receiverImage;
	}

}
