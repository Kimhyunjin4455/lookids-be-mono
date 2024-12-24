package lookids.mono.auth.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.auth.domain.Auth;
import lookids.mono.auth.domain.OAuth;
import lookids.mono.auth.dto.in.AccountDeleteKafkaRequestDto;
import lookids.mono.auth.repository.AuthRepository;
import lookids.mono.auth.repository.OAuthRepository;
import lookids.mono.common.entity.BaseResponseStatus;
import lookids.mono.common.exception.BaseException;

@Slf4j
@RequiredArgsConstructor
@Service
public class CancelAccountServiceImpl implements CancelAccountService {
	private final AuthRepository authRepository;
	private final OAuthRepository oAuthRepository;
	private final KafkaTemplate<String, AccountDeleteKafkaRequestDto> kafkaAccountDeleteTemplate;

	@Value("${account.delete}")
	private String accountDeleteTopic;

	@Override
	public void deleteAccountState(String uuid) {
		Auth auth = authRepository.findByUuid(uuid)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));

		Auth softDeletedUser = Auth.builder()
			.id(auth.getId())
			.uuid(auth.getUuid())
			.userEmail(auth.getUserEmail())
			.loginId(auth.getLoginId())
			.password(auth.getPassword())
			.isState(false)
			.deletedAt(LocalDateTime.now())
			.build();

		authRepository.save(softDeletedUser);
		AccountDeleteKafkaRequestDto softDeleteUser = AccountDeleteKafkaRequestDto.builder().uuid(uuid).build();
		kafkaAccountDeleteTemplate.send(accountDeleteTopic, softDeleteUser);
		log.info("send success delete user: {}", softDeleteUser.getUuid());

	}

	// 정책(3개월) 지난 후 스케줄러를 통해 일괄처리 예정, 현재 구현여부 미확정
	@Override
	public void deleteAccount(String uuid) {

	}

	@Override
	public void deleteSocialAccountState(String uuid) {
		OAuth oAuth = oAuthRepository.findByUuid(uuid)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_USER));

		OAuth softDeletedUser = OAuth.builder()
			.id(oAuth.getId())
			.uuid(oAuth.getUuid())
			.provider(oAuth.getProvider())
			.providerAccountId(oAuth.getProviderAccountId())
			.isState(false)
			.deletedAt(LocalDateTime.now())
			.build();

		oAuthRepository.save(softDeletedUser);
		AccountDeleteKafkaRequestDto softDeleteSocialUser = AccountDeleteKafkaRequestDto.builder().uuid(uuid).build();
		kafkaAccountDeleteTemplate.send(accountDeleteTopic, softDeleteSocialUser);
		log.info("send success delete social user: {}", softDeleteSocialUser.getUuid());

	}
}
