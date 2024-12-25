package lookids.mono.favorite.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import lookids.mono.favorite.domain.Favorite;
import lookids.mono.favorite.domain.FavoriteType;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
	Optional<Favorite> findByTargetCodeAndUuid(String targetCode, String uuid);

	List<Favorite> findByUuidAndFavoriteStateTrue(String uuid);

	Page<Favorite> findByTargetCodeAndFavoriteTypeAndFavoriteStateTrue(String targetCode, FavoriteType favoriteType,
		Pageable pageable);

	Optional<Favorite> findByUuidAndTargetCodeAndFavoriteStateTrue(String uuid, String targetCode);
}
