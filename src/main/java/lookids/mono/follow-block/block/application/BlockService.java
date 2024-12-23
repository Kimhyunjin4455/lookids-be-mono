package Lookids.block.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import Lookids.block.dto.in.BlockRequestDto;
import Lookids.block.dto.out.BlockResponseDto;

public interface BlockService {
	void updateBlock(BlockRequestDto blockRequestDto);

	// List<BlockResponseDto> readBlock(String userUuid);
	Page<BlockResponseDto> readBlock(String userUuid, Pageable pageable);
}