package lookids.mono.auth.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.mono.auth.domain.OAuth;
import lookids.mono.auth.vo.in.OAuthSignUpRequestVo;

@Getter
@NoArgsConstructor
@ToString
public class OAuthSignUpRequestDto {
	private String provider;
	private String providerAccountId;
	private String uuid;

	@Builder
	public OAuthSignUpRequestDto(String provider, String providerAccountId, String uuid) {
		this.provider = provider;
		this.providerAccountId = providerAccountId;
		this.uuid = uuid;
	}

	public static OAuthSignUpRequestDto toDto(OAuthSignUpRequestVo oAuthSignUpRequestVo) {
		return OAuthSignUpRequestDto.builder()
			.provider(oAuthSignUpRequestVo.getProvider())
			.providerAccountId(oAuthSignUpRequestVo.getProviderAccountId())
			.build();
	}

	public OAuth toEntity(String uuid) {
		return OAuth.builder().provider(provider).providerAccountId(providerAccountId).uuid(uuid).isState(true).build();
	}
}
