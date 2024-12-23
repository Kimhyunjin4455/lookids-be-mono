package lookids.auth.auth.service;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.auth.auth.dto.out.KeyResponseDto;
import lookids.auth.common.entity.BaseResponseStatus;
import lookids.auth.common.exception.BaseException;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {

	private final JavaMailSender mailSender;

	// redis
	private final RedisTemplate<String, Object> redisTemplate;

	@Override
	public KeyResponseDto sendSimpleEmail(String to) {
		SimpleMailMessage message = new SimpleMailMessage();
		String key = String.valueOf((int) (Math.random() * 900000) + 100000);
		String subject = String.format("LooKids 인증번호 %s 입력해주세요.", key);
		// 서비스로직에 전달할 6자리 랜덤 정수 인증코드와 응답 메세지 셍성
		String emailBody = String.format("안녕하세요. LooKids 입니다.\n인증코드는 %s 입니다. 감사합니다.", key);

		message.setTo(to);
		message.setSubject(subject);
		message.setText(emailBody);
		try {
			mailSender.send(message);
			log.info("Email sent to: {}", to);
		} catch (MailSendException e) {
			log.error("Failed to send email to: {}", to, e);
			throw new BaseException(BaseResponseStatus.MESSAGE_SEND_FAILED); // BaseException 발생
		}

		saveEmailVerificationCode(to, key); // Key(메일 주소) Value(인증 코드)

		return KeyResponseDto.builder()
			.verification(true)
			.build();
	}

	// 이메일 인증 코드 저장 (3분 만료)
	public void saveEmailVerificationCode(String email, String code) {
		redisTemplate.opsForValue()
			.set(
				"EmailAuth:" + email,  // 키 prefix로 구분
				code,
				Duration.ofMinutes(3).plusSeconds(3)  // 3분 후 만료
			);
	}
}
