package lookids.user.tier.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.user.common.entity.BaseResponse;
import lookids.user.tier.application.TierService;
import lookids.user.tier.dto.out.TierResponseDto;
import lookids.user.tier.vo.out.TierResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/read/tier")
public class TierReadController {

	private final TierService tierService;

	@Operation(summary = "readTierList API", description = "readTierList API 입니다.")
	@GetMapping("/all")
	public BaseResponse<List<TierResponseVo>> readTierList() {
		List<TierResponseDto> tierResponseDtoList = tierService.readTierList();
		return new BaseResponse<>(tierResponseDtoList.stream().map(TierResponseDto::toVo).toList());
	}

	@Operation(summary = "readTier API", description = "readTier API 입니다.")
	@GetMapping()
	public BaseResponse<TierResponseVo> readTier(@RequestParam(value = "tierCode") String tierCode) {
		return new BaseResponse<>(tierService.readTier(tierCode).toVo());
	}
}
