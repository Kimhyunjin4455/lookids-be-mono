package lookids.mono.batch.favorite.adaptor.out.infrastructure.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import lookids.mono.batch.favorite.adaptor.out.infrastructure.entity.FavoriteCountEntity;
import lookids.mono.batch.favorite.domain.FavoriteType;

public interface FavoriteCountRepository extends JpaRepository<FavoriteCountEntity, Long> {
	Optional<FavoriteCountEntity> findByTargetCodeAndType(String targetCode, FavoriteType type);

	@Query("SELECT f FROM FavoriteCountEntity f WHERE f.targetCode IN :targetCodes AND f.type IN :types")
	List<FavoriteCountEntity> findByTargetCodeInAndTypeIn(@Param("targetCodes") List<String> targetCodes,
		@Param("types") List<FavoriteType> types);
}
