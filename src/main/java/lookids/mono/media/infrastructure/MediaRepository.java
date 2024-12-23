package lookids.media.media.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lookids.media.media.domain.Media;

public interface MediaRepository extends JpaRepository<Media, Long> {

	List<Media> findByUserUuid(String uuid);

	List<Media> findByUserUuidAndState(String uuid, Boolean state);

	Optional<Media> findByMediaCodeAndState(String mediaCode, Boolean state);

	Optional<Media> findByMediaCodeAndUserUuidAndState(String mediaCode, String userUuid, Boolean state);

	Optional<Media> findById(Long id);
}
