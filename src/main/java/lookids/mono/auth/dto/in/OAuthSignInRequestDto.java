package lookids.mono.auth.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.mono.auth.vo.in.OAuthSignInRequestVo;

@Getter
@NoArgsConstructor
@ToString
public class OAuthSignInRequestDto {
	private String provider;
	private String providerAccountId;

	@Builder
	public OAuthSignInRequestDto(String provider, String providerAccountId) {
		this.provider = provider;
		this.providerAccountId = providerAccountId;
	}

	public static OAuthSignInRequestDto toDto(OAuthSignInRequestVo oAuthSignInRequestVo) {
		return OAuthSignInRequestDto.builder()
			.provider(oAuthSignInRequestVo.getProvider())
			.providerAccountId(oAuthSignInRequestVo.getProviderAccountId())
			.build();
	}

}

