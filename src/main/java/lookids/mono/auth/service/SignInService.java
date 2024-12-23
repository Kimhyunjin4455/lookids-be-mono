package lookids.auth.auth.service;

import lookids.auth.auth.dto.in.OAuthSignUpRequestDto;
import lookids.auth.auth.dto.in.RefreshTokenRequestDto;
import lookids.auth.auth.dto.in.SignInRequestDto;
import lookids.auth.auth.dto.out.RefreshTokenResponseDto;
import lookids.auth.auth.dto.out.SignInResponseDto;

public interface SignInService {
	SignInResponseDto signIn(SignInRequestDto signInRequestDto);
	RefreshTokenResponseDto refreshAccessToken(RefreshTokenRequestDto refreshTokenRequestDto);
	SignInResponseDto oAuthSign(OAuthSignUpRequestDto oAuthRequestDto);


}
