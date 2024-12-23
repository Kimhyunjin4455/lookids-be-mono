package lookids.alarm.notification.vo.in;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class NotificationRequestVo { // kafka로 부터 받을 것이기에 미사용
	private String senderUuid;
	private List<String> receiverUuidList;
	private String content;
	private String type;
}
