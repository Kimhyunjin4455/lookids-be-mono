package lookids.mono.manager.information.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lookids.mono.manager.information.domain.Information;

public interface InformationRepository extends JpaRepository<Information, Long> {

	Optional<Information> findByFeedCode(String feedCode);

	List<Information> findByManagerUuid(String managerUuid);

	void deleteByFeedCode(String feedCode);

}
