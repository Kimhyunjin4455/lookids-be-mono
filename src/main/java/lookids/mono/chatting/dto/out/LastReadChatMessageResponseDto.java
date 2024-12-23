package lookids.chatting.chatting.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import lookids.chatting.chatting.vo.out.LastReadMessageResponseVo;

@Slf4j
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class LastReadChatMessageResponseDto {
	private String userId;
	private String lastReadChatMessageId;

	public static LastReadChatMessageResponseDto toDto(String userId, String lastReadChatMessageId) {
		return LastReadChatMessageResponseDto.builder()
			.userId(userId)
			.lastReadChatMessageId(lastReadChatMessageId)
			.build();
	}

	public LastReadMessageResponseVo toVo() {
		return LastReadMessageResponseVo.builder()
			.userId(userId)
			.lastReadChatMessageId(lastReadChatMessageId)
			.build();
	}
}
