package lookids.auth.auth.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.auth.auth.vo.out.KeyResponseVo;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeyResponseDto {
	private Boolean verification;

	public static KeyResponseVo toVo(KeyResponseDto keyResponseDto) {
		return KeyResponseVo.builder()
			.verification(keyResponseDto.getVerification())
			.build();
	}
}