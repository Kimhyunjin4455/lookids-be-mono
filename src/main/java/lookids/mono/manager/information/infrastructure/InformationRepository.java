package lookids.manager.information.infrastructure;

import lookids.manager.information.domain.Information;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InformationRepository extends JpaRepository<Information, Long> {

	Optional<Information> findByFeedCode(String feedCode);
	List<Information> findByManagerUuid(String managerUuid);
	void deleteByFeedCode(String feedCode);

}
