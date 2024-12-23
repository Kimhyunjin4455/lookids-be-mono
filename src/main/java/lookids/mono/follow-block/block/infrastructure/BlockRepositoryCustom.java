package Lookids.block.infrastructure;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import Lookids.block.dto.out.BlockResponseDto;

public interface BlockRepositoryCustom {

	Page<BlockResponseDto> findByUuidAndStateTrue(String uuid, Pageable pageable);

}
