package lookids.mono.user.point.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lookids.mono.user.point.domain.Point;

public interface PointRepository extends JpaRepository<Point, Long> {

	List<Point> findByUserUuid(String uuid);

	List<Point> findByUserUuidAndType(String userUuid, Boolean type);

	Optional<Point> findById(Long id);
}
