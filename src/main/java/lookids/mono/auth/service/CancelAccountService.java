package lookids.auth.auth.service;

public interface CancelAccountService {
	void deleteAccountState(String uuid);
	void deleteAccount(String uuid);
	void deleteSocialAccountState(String uuid);
}
