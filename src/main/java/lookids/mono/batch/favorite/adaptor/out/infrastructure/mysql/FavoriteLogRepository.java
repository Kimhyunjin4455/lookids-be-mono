package lookids.batch.favorite.adaptor.out.infrastructure.mysql;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lookids.batch.favorite.adaptor.out.infrastructure.entity.FavoriteLogEntity;

@Repository
public interface FavoriteLogRepository extends JpaRepository<FavoriteLogEntity, Long> {
	Page<FavoriteLogEntity> findByProcessedFalse(Pageable pageable);
}
