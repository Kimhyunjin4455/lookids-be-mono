package lookids.mono.notification.vo.out;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class NotificationResponseVo {
	private String senderUuid;
	private List<String> receiverUuidList;
	private String content;
	private String type;
	private LocalDateTime createdAt;

	@Builder
	public NotificationResponseVo(String senderUuid, List<String> receiverUuidList, String content, String type,
		LocalDateTime createdAt) {
		this.senderUuid = senderUuid;
		this.receiverUuidList = receiverUuidList;
		this.content = content;
		this.type = type;
		this.createdAt = createdAt;
	}

}
