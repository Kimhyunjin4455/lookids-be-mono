package Lookids.block.dto.out;

import Lookids.block.domain.Block;
import Lookids.block.vo.out.BlockResponseVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BlockResponseDto {
	private String uuid;
	private String blockedUuid;

	@Builder
	public BlockResponseDto(String uuid, String blockedUuid) {
		this.uuid = uuid;
		this.blockedUuid = blockedUuid;
	}

	public static BlockResponseDto toDto(Block block) {
		return BlockResponseDto.builder()
			.uuid(block.getUuid())
			.blockedUuid(block.getBlockedUuid())
			.build();
	}

	public static BlockResponseVo toVo(BlockResponseDto blockResponseDto) {
		return BlockResponseVo.builder()
			.uuid(blockResponseDto.getUuid())
			.blockedUuid(blockResponseDto.getBlockedUuid())
			.build();
	}
	
}
