package lookids.mono.followblock.block.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lookids.mono.followblock.block.domain.Block;

public interface BlockRepository extends JpaRepository<Block, Long>, BlockRepositoryCustom {

	// List<Block> findByUuidAndStateTrue(String userUuid);
	Optional<Block> findByUuidAndBlockedUuid(String uuid, String blockedUuid);

	List<Block> findByUuidAndStateTrue(String uuid);

}