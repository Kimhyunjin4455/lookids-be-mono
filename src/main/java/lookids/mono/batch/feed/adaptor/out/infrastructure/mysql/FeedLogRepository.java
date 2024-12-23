package lookids.batch.feed.adaptor.out.infrastructure.mysql;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lookids.batch.feed.adaptor.out.infrastructure.entity.FeedLogEntity;

@Repository
public interface FeedLogRepository extends JpaRepository<FeedLogEntity, Long> {
	Optional<FeedLogEntity> findByFeedCode(String feedCode);

	Page<FeedLogEntity> findByProcessedFalse(Pageable pageable);
}
