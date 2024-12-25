package lookids.mono.chatting.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CheckOneToOneChatResponseVo {
	private Boolean result;
	private String roomId;
}
