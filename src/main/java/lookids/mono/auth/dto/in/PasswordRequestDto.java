package lookids.mono.auth.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.mono.auth.vo.in.PasswordRequestVo;

@Getter
@NoArgsConstructor
@ToString
public class PasswordRequestDto {
	private String password;
	private String uuid;

	@Builder
	public PasswordRequestDto(String password, String uuid) {
		this.password = password;
		this.uuid = uuid;
	}

	public static PasswordRequestDto toDto(PasswordRequestVo passwordRequestVo, String uuid) {
		return PasswordRequestDto.builder().password(passwordRequestVo.getPassword()).uuid(uuid).build();
	}
}
