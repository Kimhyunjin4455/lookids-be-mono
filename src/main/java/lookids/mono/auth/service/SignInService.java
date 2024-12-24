package lookids.mono.auth.service;

import lookids.mono.auth.dto.in.OAuthSignUpRequestDto;
import lookids.mono.auth.dto.in.RefreshTokenRequestDto;
import lookids.mono.auth.dto.in.SignInRequestDto;
import lookids.mono.auth.dto.out.RefreshTokenResponseDto;
import lookids.mono.auth.dto.out.SignInResponseDto;

public interface SignInService {
	SignInResponseDto signIn(SignInRequestDto signInRequestDto);

	RefreshTokenResponseDto refreshAccessToken(RefreshTokenRequestDto refreshTokenRequestDto);

	SignInResponseDto oAuthSign(OAuthSignUpRequestDto oAuthRequestDto);

}
