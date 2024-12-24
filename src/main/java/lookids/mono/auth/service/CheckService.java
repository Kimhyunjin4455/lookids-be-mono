package lookids.mono.auth.service;

import lookids.mono.auth.dto.in.EmailVerifyRequestDto;
import lookids.mono.auth.dto.in.KeyRequestDto;
import lookids.mono.auth.dto.in.PasswordRequestDto;
import lookids.mono.auth.dto.out.KeyResponseDto;
import lookids.mono.auth.dto.out.LoginIdFindResponseDto;

public interface CheckService {
	KeyResponseDto verifyPassword(PasswordRequestDto passwordRequestDto);

	KeyResponseDto verifyEmail(KeyRequestDto keyRequestDto);

	KeyResponseDto verifyLoginId(KeyRequestDto keyRequestDto);

	KeyResponseDto checkVerificationCode(EmailVerifyRequestDto emailVerifyRequestDto);

	LoginIdFindResponseDto findIdByEmail(KeyRequestDto keyRequestDto);

	void putPassword(PasswordRequestDto passwordRequestDto);
}
