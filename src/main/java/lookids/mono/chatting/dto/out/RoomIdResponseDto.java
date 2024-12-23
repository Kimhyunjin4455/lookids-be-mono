package lookids.chatting.chatting.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.chatting.chatting.vo.out.RoomIdResponseVo;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RoomIdResponseDto {
	private String roomId;

	public static RoomIdResponseDto toDto(String roomId) {
		return RoomIdResponseDto.builder()
			.roomId(roomId)
			.build();
	}

	public RoomIdResponseVo toVo() {
		return RoomIdResponseVo.builder()
			.roomId(roomId)
			.build();
	}
}
