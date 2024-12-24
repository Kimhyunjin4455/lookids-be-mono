package lookids.mono.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lookids.mono.auth.domain.OAuth;

public interface OAuthRepository extends JpaRepository<OAuth, Long> {
	Optional<OAuth> findByUuid(String uuid);

	Optional<OAuth> findByProviderAccountId(String providerAccountId);

	Optional<OAuth> findByProviderAndProviderAccountId(String provider, String providerAccountId);

	Optional<OAuth> findByProviderAndUuid(String provider, String uuid);

	boolean existsByUuid(String uuid);
}