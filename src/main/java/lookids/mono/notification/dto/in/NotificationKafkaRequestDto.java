package lookids.alarm.notification.dto.in;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// todo: 테스트용 Controller에서 사용했던 코드, 추후 controller와 함께 삭제 예정
@Getter
@NoArgsConstructor
public class NotificationKafkaRequestDto {
	private String senderUuid;
	private List<String> receiverUuidList;
	private String content;
	private String type;

	@Builder
	public NotificationKafkaRequestDto(
		String senderUuid,
		List<String> receiverUuidList,
		String content,
		String type
	) {
		this.senderUuid = senderUuid;
		this.receiverUuidList = receiverUuidList;
		this.content = content;
		this.type = type;
	}

}
