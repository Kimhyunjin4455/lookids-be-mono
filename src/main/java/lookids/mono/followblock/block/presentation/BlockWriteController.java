package lookids.mono.followblock.block.presentation;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lookids.mono.common.entity.BaseResponse;
import lookids.mono.common.entity.BaseResponseStatus;
import lookids.mono.followblock.block.application.BlockService;
import lookids.mono.followblock.block.dto.in.BlockRequestDto;
import lookids.mono.followblock.block.vo.in.BlockRequestVo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow-block-service/write/block")
public class BlockWriteController {

	private final BlockService blockService;

	@Operation(summary = "updateBlock API", description = "updateBlock API")
	@PutMapping
	public BaseResponse<Void> createDeleteBlock(@RequestHeader String uuid,
		@RequestBody BlockRequestVo blockRequestVo) {
		blockService.updateBlock(BlockRequestDto.toDto(uuid, blockRequestVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

}
