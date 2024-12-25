package lookids.mono.followblock.block.dto.in;

import lombok.Builder;
import lombok.Getter;
import lookids.mono.followblock.block.domain.Block;
import lookids.mono.followblock.block.vo.in.BlockRequestVo;

@Getter
public class BlockRequestDto {
	private Long id;
	private String uuid;
	private String blockedUuid;
	private Boolean state;

	@Builder
	public BlockRequestDto(Long id, String uuid, String blockedUuid, Boolean state) {
		this.id = id;
		this.uuid = uuid;
		this.blockedUuid = blockedUuid;
		this.state = state;
	}

	public static BlockRequestDto toDto(String uuid, BlockRequestVo blockRequestVo) {
		return BlockRequestDto.builder().uuid(uuid).blockedUuid(blockRequestVo.getBlockedUuid()).build();
	}

	public Block toEntity() {
		return Block.builder().uuid(uuid).blockedUuid(blockedUuid).state(true).build();
	}

	public static Block toUpdateEntity(Block block) {
		return Block.builder()
			.id(block.getId())
			.uuid(block.getUuid())
			.blockedUuid(block.getBlockedUuid())
			.state(!block.isState())
			.build();
	}

}
