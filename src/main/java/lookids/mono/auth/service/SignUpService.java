package lookids.auth.auth.service;

import lookids.auth.auth.dto.in.SignUpRequestDto;

public interface SignUpService {
	void signUp(SignUpRequestDto loginRequestDto);

}
