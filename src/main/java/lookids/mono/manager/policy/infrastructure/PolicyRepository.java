package lookids.mono.manager.policy.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lookids.mono.manager.policy.domain.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Long> {

	Optional<Policy> findByPolicyCode(String policyCode);

	void deleteByPolicyCode(String policyCode);

}
