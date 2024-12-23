package lookids.user.userprofile.vo.in;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FeedEventVo {
	private String feedCode;
	private String uuid;
	private List<String> petCode;
	private String content;
	private List<String> tags;
	private boolean state;
	private List<String> mediaUrl;
	private LocalDateTime createdAt;
}
