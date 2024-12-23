package lookids.auth.auth.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class PostUserRequestDto {
	private String uuid;
	private String nickname;

	@Builder
	public PostUserRequestDto(
		String uuid,
		String nickname
	) {
		this.uuid = uuid;
		this.nickname = nickname;
	}

	public static PostUserRequestDto toDto(String uuid, String nickname) {
		return PostUserRequestDto.builder()
			.uuid(uuid)
			.nickname(nickname)
			.build();
	}
}
