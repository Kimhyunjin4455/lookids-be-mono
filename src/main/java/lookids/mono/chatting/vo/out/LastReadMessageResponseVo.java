package lookids.mono.chatting.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LastReadMessageResponseVo {
	private String userId;
	private String lastReadChatMessageId;
}
