package lookids.mono.common.config;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RedisHash(value = "token", timeToLive = 60 * 60 * 24) // 토큰의 만료 시간 설정 (1일)
public class RefreshToken {
	@Id
	private String refreshToken;
	private String uuid;

	public RefreshToken(String refreshToken, String uuid) {
		this.refreshToken = refreshToken; // 회원의 uuid
		this.uuid = uuid;
	}
}
