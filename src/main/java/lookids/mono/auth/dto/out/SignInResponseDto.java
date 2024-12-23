package lookids.auth.auth.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.auth.auth.vo.out.SignInResponseVo;

@Getter
@NoArgsConstructor
public class SignInResponseDto {

	private String accessToken;
	private String refreshToken;
	private String uuid;

	@Builder
	public SignInResponseDto(
		String accessToken,
		String refreshToken,
		String uuid
	) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.uuid = uuid;
	}


	public static SignInResponseDto toDto(String accessToken, String refreshToken, String uuid){
		return SignInResponseDto.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.uuid(uuid)
			.build();
	}

	public SignInResponseVo toVo(){
		return SignInResponseVo.builder()
			.accessToken(accessToken)
			.refreshToken(refreshToken)
			.uuid(uuid)
			.build();
	}

}
