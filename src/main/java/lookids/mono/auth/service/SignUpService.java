package lookids.mono.auth.service;

import lookids.mono.auth.dto.in.SignUpRequestDto;

public interface SignUpService {
	void signUp(SignUpRequestDto loginRequestDto);

}
