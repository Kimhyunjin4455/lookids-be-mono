package lookids.mono.user.userprofile.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lookids.mono.user.userprofile.domain.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

	Optional<UserProfile> findByUserUuid(String uuid);

	Optional<UserProfile> findByNicknameAndTag(String nickname, String tage);

	Boolean existsByNicknameAndTag(String nickname, String tag);

	Optional<UserProfile> findById(Long id);
}
