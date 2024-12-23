package lookids.auth.auth.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class AccountDeleteKafkaRequestDto {
	String uuid;

	@Builder
	public AccountDeleteKafkaRequestDto(
		String uuid
	) {
		this.uuid = uuid;
	}
}
