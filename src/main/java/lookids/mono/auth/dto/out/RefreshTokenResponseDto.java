package lookids.mono.auth.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.auth.vo.out.RefreshTokenResponseVo;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshTokenResponseDto {
	private String accessToken;
	private Long accessTokenExpiredTime;

	public static RefreshTokenResponseDto toDto(String accessToken, Long accessTokenExpiredTime) {
		return RefreshTokenResponseDto.builder()
			.accessToken(accessToken)
			.accessTokenExpiredTime(accessTokenExpiredTime)
			.build();
	}

	public RefreshTokenResponseVo toVo() {
		return RefreshTokenResponseVo.builder()
			.accessToken(accessToken)
			.accessTokenExpiredTime(accessTokenExpiredTime)
			.build();
	}
}
