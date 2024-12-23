package lookids.user.userprofile.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class FollowKafkaVo {
	private String senderUuid;
	private String senderNickname;
	private String senderTag;
	private String senderImage;
	
	private String receiverUuid;
	private String receiverNickname;
	private String receiverTag;
	private String receiverImage;
}
