package lookids.auth.auth.service;

import lookids.auth.auth.dto.out.KeyResponseDto;
import lookids.auth.auth.vo.out.KeyResponseVo;

public interface EmailService {
	KeyResponseDto sendSimpleEmail(String to);
}
