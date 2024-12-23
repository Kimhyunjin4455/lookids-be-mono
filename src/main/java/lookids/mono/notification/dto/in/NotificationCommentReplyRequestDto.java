package lookids.alarm.notification.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NotificationCommentReplyRequestDto {
	private String uuid;
	private String receiverUuid; // 현재 대댓글 서비스의 Producer는 이 값 전달X
	private String content;
	private String feedCode;


	@Builder
	public NotificationCommentReplyRequestDto(
		String uuid,
		String receiverUuid,
		String content,
		String feedCode
	) {
		this.uuid = uuid;
		this.receiverUuid = receiverUuid;
		this.content = content;
		this.feedCode = feedCode;
	}
}
