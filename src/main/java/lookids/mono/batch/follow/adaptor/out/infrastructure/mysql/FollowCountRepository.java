package lookids.mono.batch.follow.adaptor.out.infrastructure.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lookids.mono.batch.follow.adaptor.out.infrastructure.entity.FollowCountEntity;

public interface FollowCountRepository extends JpaRepository<FollowCountEntity, Long> {
	Optional<FollowCountEntity> findByUuid(String uuid);

	@Query("SELECT f FROM FollowCountEntity f WHERE f.uuid IN :uuids")
	List<FollowCountEntity> findByUuidIn(@Param("uuids") List<String> uuids);
}
