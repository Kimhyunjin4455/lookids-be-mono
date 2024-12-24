package lookids.mono.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lookids.mono.auth.domain.Auth;

public interface AuthRepository extends JpaRepository<Auth, Long> {
	Optional<Auth> findByLoginId(String loginId);

	Optional<Auth> findByUuid(String uuid);

	Optional<Auth> findByUserEmail(String email);

	boolean existsByUuid(String uuid);
}
