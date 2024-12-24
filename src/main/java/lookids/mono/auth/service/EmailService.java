package lookids.mono.auth.service;

import lookids.mono.auth.dto.out.KeyResponseDto;

public interface EmailService {
	KeyResponseDto sendSimpleEmail(String to);
}
