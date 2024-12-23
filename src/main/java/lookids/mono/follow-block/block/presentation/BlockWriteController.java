package Lookids.block.presentation;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Lookids.block.application.BlockService;
import Lookids.block.dto.in.BlockRequestDto;
import Lookids.block.vo.in.BlockRequestVo;
import Lookids.common.entity.BaseResponse;
import Lookids.common.entity.BaseResponseStatus;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/write/block")
public class BlockWriteController {

	private final BlockService blockService;

	@Operation(summary = "updateBlock API", description = "updateBlock API")
	@PutMapping
	public BaseResponse<Void> createDeleteBlock(@RequestHeader String uuid, @RequestBody BlockRequestVo blockRequestVo) {
		blockService.updateBlock(BlockRequestDto.toDto(uuid, blockRequestVo));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

}
