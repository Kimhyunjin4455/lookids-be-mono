package lookids.mono.auth.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.mono.auth.vo.in.KeyRequestVo;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class KeyRequestDto {
	private String key;

	public static KeyRequestDto toDto(KeyRequestVo keyRequestVo) {
		return KeyRequestDto.builder().key(keyRequestVo.getKey()).build();
	}
}
