package lookids.map.map.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lookids.map.map.domain.Map;

public interface MapRepository extends JpaRepository<Map, Long>, MapRepositoryCustom {

	List<Map> findByUuid(String uuid);
	List<Map> findByUuidAndStateCheckedTrue(String uuid);
	Optional<Map> findByPinCode(String pinCode);

	void deleteByFeedCode(String feedCode);

}
