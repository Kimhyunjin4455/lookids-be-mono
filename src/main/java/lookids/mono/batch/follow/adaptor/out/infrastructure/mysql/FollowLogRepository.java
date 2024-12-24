package lookids.mono.batch.follow.adaptor.out.infrastructure.mysql;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lookids.mono.batch.follow.adaptor.out.infrastructure.entity.FollowLogEntity;

@Repository
public interface FollowLogRepository extends JpaRepository<FollowLogEntity, Long> {
	Page<FollowLogEntity> findByProcessedFalse(Pageable pageable);
}
