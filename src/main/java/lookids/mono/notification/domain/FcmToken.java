package lookids.alarm.notification.domain;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "fcm_token")
@Getter
@NoArgsConstructor
@ToString
public class FcmToken {
	@Id
	private ObjectId id;
	private String uuid;
	private List<String> fcmTokenList;

	@Builder
	public FcmToken(
		ObjectId id,
		String uuid,
		List<String> fcmTokenList
	) {
		this.id = id;
		this.uuid = uuid;
		this.fcmTokenList = fcmTokenList;
	}
}
