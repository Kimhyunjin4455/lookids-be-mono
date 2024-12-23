package lookids.alarm.notification.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "notification")
@Getter
@NoArgsConstructor
@ToString
public class Notification {
	@Id
	private ObjectId id;
	private String senderUuid;
	private List<String> receiverUuidList;
	private String title;
	private String content;
	private String mediaUrl;
	private String feedCode;
	private String roomId;
	private NotificationType type; // 직렬화, 역직렬화? -> Enum으로 변경
	private LocalDateTime createdAt; // 값은 dto에서 추가, 가져올때는 LocalDateTime으로 변환해서 가져오기

	@Builder
	public Notification(
		ObjectId id,
		String senderUuid,
		List<String> receiverUuidList,
		String title,
		String content,
		String mediaUrl,
		String feedCode,
		String roomId,
		NotificationType type,
		LocalDateTime createdAt
	) {
		this.id = id;
		this.senderUuid = senderUuid;
		this.receiverUuidList =receiverUuidList;
		this.title = title;
		this.content = content;
		this.mediaUrl = mediaUrl;
		this.feedCode = feedCode;
		this.roomId = roomId;
		this.type = type;
		this.createdAt = createdAt;
	}
}
