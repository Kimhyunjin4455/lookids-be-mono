package lookids.mono.batch.feed.adaptor.out.infrastructure.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lookids.mono.batch.feed.adaptor.out.infrastructure.entity.FeedCountEntity;

public interface FeedCountRepository extends JpaRepository<FeedCountEntity, Long> {
	Optional<FeedCountEntity> findByUuid(String uuid);

	@Query("SELECT f FROM FeedCountEntity f WHERE f.uuid IN :uuids")
	List<FeedCountEntity> findByUuidIn(@Param("uuids") List<String> uuids);
}
