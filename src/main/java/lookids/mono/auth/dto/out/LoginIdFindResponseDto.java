package lookids.mono.auth.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.auth.vo.out.LoginIdFindResponseVo;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginIdFindResponseDto {
	private String loginId;

	public static LoginIdFindResponseDto toDto(String loginId) {
		return LoginIdFindResponseDto.builder().loginId(loginId).build();
	}

	public LoginIdFindResponseVo toVo(LoginIdFindResponseDto loginIdFindResponseDto) {
		return LoginIdFindResponseVo.builder().loginId(loginIdFindResponseDto.getLoginId()).build();
	}
}
