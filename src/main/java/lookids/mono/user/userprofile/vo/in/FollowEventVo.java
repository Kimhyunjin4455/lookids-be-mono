package lookids.mono.user.userprofile.vo.in;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FollowEventVo {
	private String receiverUuid;
	private String senderUuid;
	private LocalDateTime createdAt;
}