package lookids.user.tier.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lookids.user.tier.domain.Tier;

public interface TierRepository extends JpaRepository<Tier, Long> {

	Optional<Tier> findByTierCode(String tierCode);

	List<Tier> findAll();

	Optional<Tier> findById(Long id);
}
