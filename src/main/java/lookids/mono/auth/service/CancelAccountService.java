package lookids.mono.auth.service;

public interface CancelAccountService {
	void deleteAccountState(String uuid);

	void deleteAccount(String uuid);

	void deleteSocialAccountState(String uuid);
}
