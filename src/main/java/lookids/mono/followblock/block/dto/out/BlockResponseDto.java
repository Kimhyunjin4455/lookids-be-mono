package lookids.mono.followblock.block.dto.out;

import lombok.Builder;
import lombok.Getter;
import lookids.mono.followblock.block.domain.Block;
import lookids.mono.followblock.block.vo.out.BlockResponseVo;

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
		return BlockResponseDto.builder().uuid(block.getUuid()).blockedUuid(block.getBlockedUuid()).build();
	}

	public static BlockResponseVo toVo(BlockResponseDto blockResponseDto) {
		return BlockResponseVo.builder()
			.uuid(blockResponseDto.getUuid())
			.blockedUuid(blockResponseDto.getBlockedUuid())
			.build();
	}

}
