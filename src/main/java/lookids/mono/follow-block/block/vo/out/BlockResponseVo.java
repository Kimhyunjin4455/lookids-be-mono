package Lookids.block.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BlockResponseVo {

	private String uuid;
	private String blockedUuid;

	@Builder
	public BlockResponseVo(String uuid, String blockedUuid) {
		this.uuid = uuid;
		this.blockedUuid = blockedUuid;
	}

}
