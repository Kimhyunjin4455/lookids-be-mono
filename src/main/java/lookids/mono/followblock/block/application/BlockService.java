package lookids.mono.followblock.block.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lookids.mono.followblock.block.dto.in.BlockRequestDto;
import lookids.mono.followblock.block.dto.out.BlockResponseDto;

public interface BlockService {
	void updateBlock(BlockRequestDto blockRequestDto);

	// List<BlockResponseDto> readBlock(String userUuid);
	Page<BlockResponseDto> readBlock(String userUuid, Pageable pageable);
}