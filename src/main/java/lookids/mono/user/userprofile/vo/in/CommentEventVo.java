package lookids.mono.user.userprofile.vo.in;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentEventVo {
	private String commentCode;
	private String feedCode;
	private String feedUuid;
	private String uuid;
	private String content;
	private LocalDateTime createdAt;
	private String parentCommentCode;
}
