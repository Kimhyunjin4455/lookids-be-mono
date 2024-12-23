package lookids.chatting.chatting.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.chatting.chatting.vo.out.CheckOneToOneChatResponseVo;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class CheckOneToOneChatResponseDto {
	private Boolean result;
	private String roomId;

	public static CheckOneToOneChatResponseDto toDto(Boolean result, String roomId) {
		return CheckOneToOneChatResponseDto.builder()
			.result(result)
			.roomId(roomId)
			.build();
	}

	public CheckOneToOneChatResponseVo toVo() {
		return CheckOneToOneChatResponseVo.builder()
			.result(result)
			.roomId(roomId)
			.build();
	}
}
