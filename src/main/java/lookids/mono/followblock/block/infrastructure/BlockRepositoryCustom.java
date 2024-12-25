package lookids.mono.followblock.block.infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lookids.mono.followblock.block.dto.out.BlockResponseDto;

public interface BlockRepositoryCustom {

	Page<BlockResponseDto> findByUuidAndStateTrue(String uuid, Pageable pageable);

}
