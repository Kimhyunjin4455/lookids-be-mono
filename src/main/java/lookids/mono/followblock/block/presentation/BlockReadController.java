package lookids.mono.followblock.block.presentation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lookids.mono.common.entity.BaseResponse;
import lookids.mono.followblock.block.application.BlockService;
import lookids.mono.followblock.block.dto.out.BlockResponseDto;
import lookids.mono.followblock.block.vo.out.BlockResponseVo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow-block-service/read/block")
public class BlockReadController {

	private final BlockService blockService;

	@Operation(summary = "readBlock API", description = "readBlock API")
	@GetMapping
	public BaseResponse<Page<BlockResponseVo>> readBlock(@RequestHeader String uuid,
		@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		Pageable pageable = PageRequest.of(page, size);

		Page<BlockResponseDto> blockResponseDtoList = blockService.readBlock(uuid, pageable);
		Page<BlockResponseVo> blockResponseVo = blockResponseDtoList.map(BlockResponseDto::toVo);

		return new BaseResponse<>(blockResponseVo);
	}

}
