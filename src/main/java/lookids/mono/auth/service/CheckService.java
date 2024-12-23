package lookids.auth.auth.service;

import lookids.auth.auth.dto.in.EmailVerifyRequestDto;
import lookids.auth.auth.dto.in.KeyRequestDto;
import lookids.auth.auth.dto.in.PasswordRequestDto;
import lookids.auth.auth.dto.out.KeyResponseDto;
import lookids.auth.auth.dto.out.LoginIdFindResponseDto;

public interface CheckService {
	KeyResponseDto verifyPassword(PasswordRequestDto passwordRequestDto);
	KeyResponseDto verifyEmail(KeyRequestDto keyRequestDto);
	KeyResponseDto verifyLoginId(KeyRequestDto keyRequestDto);
	KeyResponseDto checkVerificationCode(EmailVerifyRequestDto emailVerifyRequestDto);
	LoginIdFindResponseDto findIdByEmail(KeyRequestDto keyRequestDto);
	void putPassword(PasswordRequestDto passwordRequestDto);
}
