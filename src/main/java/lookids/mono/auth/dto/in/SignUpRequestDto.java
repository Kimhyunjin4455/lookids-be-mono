package lookids.auth.auth.dto.in;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.auth.auth.domain.Auth;
import lookids.auth.auth.vo.in.SignUpRequestVo;

@Getter
@NoArgsConstructor
@ToString
public class SignUpRequestDto {
	private String loginId;
	private String password;
	private String email;
	private String nickname;
	private String uuid;

	@Builder
	public SignUpRequestDto(
		String loginId,
		String password,
		String email,
		String nickname,
		String uuid
	) {
		this.loginId = loginId;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
		this.uuid = uuid;
	}



	public static SignUpRequestDto toDto(SignUpRequestVo signUpRequestVo){
		return SignUpRequestDto.builder()
			.loginId(signUpRequestVo.getLoginId())
			.password(signUpRequestVo.getPassword())
			.email(signUpRequestVo.getEmail())
			.nickname(signUpRequestVo.getNickname())
			.build();
	}

	// 회원가입과 정책 설정에 관해 uuid 필드처리에 대해 고민 필요

	public Auth toEntity(PasswordEncoder passwordEncoder, String createdUuid){
		return Auth.builder()
			.uuid(createdUuid)
			.loginId(loginId)
			.userEmail(email)
			.password(passwordEncoder.encode(password))
			.isState(true)
			.build();
	}


}
