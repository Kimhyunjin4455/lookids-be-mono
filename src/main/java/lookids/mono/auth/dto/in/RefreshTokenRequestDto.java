package lookids.auth.auth.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class RefreshTokenRequestDto {

	private String refreshToken;
	private String uuid;

	@Builder
	public RefreshTokenRequestDto(
		String refreshToken,
		String uuid
	) {
		this.refreshToken = refreshToken;
		this.uuid = uuid;
	}

	public static RefreshTokenRequestDto toDto(String refreshToken, String uuid) {
		return RefreshTokenRequestDto.builder()
			.refreshToken(refreshToken)
			.uuid(uuid)
			.build();
	}

}
