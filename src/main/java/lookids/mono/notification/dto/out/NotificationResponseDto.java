package lookids.alarm.notification.dto.out;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.alarm.notification.domain.Notification;
import lookids.alarm.notification.vo.out.NotificationResponseVo;

@Getter
@NoArgsConstructor
public class NotificationResponseDto {

	private String senderUuid;
	private List<String> receiverUuidList;
	private String content;
	private String type;
	private LocalDateTime createdAt;

	@Builder
	public NotificationResponseDto(
		String senderUuid,
		List<String> receiverUuidList,
		String content,
		String type,
		LocalDateTime createdAt
	) {
		this.senderUuid = senderUuid;
		this.receiverUuidList = receiverUuidList;
		this.content = content;
		this.type = type;
		this.createdAt = createdAt;
	}

	public static NotificationResponseDto toDto(Notification notification){
		return new NotificationResponseDto(
			notification.getSenderUuid(),
			notification.getReceiverUuidList(),
			notification.getContent(),
			notification.getType().name(),
			notification.getCreatedAt()
		);
	}

	public NotificationResponseVo toVo(){
		return NotificationResponseVo.builder()
			.senderUuid(senderUuid)
			.receiverUuidList(receiverUuidList)
			.content(content)
			.type(type)
			.createdAt(createdAt)
			.build();
	}

}
