package lookids.auth.auth.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.auth.auth.vo.in.EmailVerifyRequestVo;

@Getter
@NoArgsConstructor
@ToString
public class EmailVerifyRequestDto {
	private String email;
	private String key;

	@Builder
	public EmailVerifyRequestDto(
		String email,
		String key
	) {
		this.email = email;
		this.key = key;
	}

	public static EmailVerifyRequestDto toDto(EmailVerifyRequestVo emailVerifyRequestVo) {
		return EmailVerifyRequestDto.builder()
			.email(emailVerifyRequestVo.getEmail())
			.key(emailVerifyRequestVo.getKey())
			.build();
	}
}
