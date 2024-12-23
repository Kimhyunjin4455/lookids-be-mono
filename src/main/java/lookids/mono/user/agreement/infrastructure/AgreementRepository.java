package lookids.user.agreement.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lookids.user.agreement.domain.Agreement;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {

	List<Agreement> findByUserUuidAndActivated(String uuid, Boolean activated);

	List<Agreement> findByPolicyCode(String policyCode);

	Optional<Agreement> findByUserUuidAndTypeAndActivated(String userUuid, String type, Boolean activated);

	Optional<Agreement> findByUserUuidAndPolicyCode(String userUuid, String policyCode);

	Optional<Agreement> findById(Long id);
}
