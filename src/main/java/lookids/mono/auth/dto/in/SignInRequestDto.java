package lookids.auth.auth.dto.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.auth.auth.vo.in.SignInRequestVo;
import lookids.auth.auth.vo.in.SignUpRequestVo;

@Getter
@NoArgsConstructor
public class SignInRequestDto {

	private String loginId;
	private String password;

	@Builder
	public SignInRequestDto(
		String loginId,
		String password
	) {
		this.loginId = loginId;
		this.password = password;
	}

	public static SignInRequestDto toDto(SignInRequestVo signInRequestVo){
		return SignInRequestDto.builder()
			.loginId(signInRequestVo.getLoginId())
			.password(signInRequestVo.getPassword())
			.build();
	}


}