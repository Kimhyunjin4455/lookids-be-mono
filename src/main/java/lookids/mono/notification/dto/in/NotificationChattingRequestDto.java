package lookids.mono.notification.dto.in;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotificationChattingRequestDto {
	private String senderUuid;
	private List<String> receiverUuidList;
	private String roomId;
	private String content;
	private String mediaUrl; // url 정보 대부분 지우고 마지막의 몇 글자 정보만 받음
	private String type;

	@Builder
	public NotificationChattingRequestDto(String senderUuid, List<String> receiverUuidList, String roomId,
		String content, String mediaUrl, String type) {
		this.senderUuid = senderUuid;
		this.receiverUuidList = receiverUuidList;
		this.roomId = roomId;
		this.content = content;
		this.mediaUrl = mediaUrl;
		this.type = type;
	}
}
