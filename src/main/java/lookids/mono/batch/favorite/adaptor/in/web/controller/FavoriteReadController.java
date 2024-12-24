package lookids.mono.batch.favorite.adaptor.in.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.batch.favorite.adaptor.in.web.mapper.FavoriteWebVoMapper;
import lookids.mono.batch.favorite.adaptor.in.web.out.FavoriteCountResponseVo;
import lookids.mono.batch.favorite.application.port.in.FavoriteCountReadUseCase;
import lookids.mono.batch.favorite.domain.FavoriteType;
import lookids.mono.common.entity.BaseResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/read/favorite")
public class FavoriteReadController {
	private final FavoriteCountReadUseCase favoriteCountReadUseCase;
	private final FavoriteWebVoMapper favoriteWebVoMapper;

	@Operation(summary = "readFavoriteCount API", description = "readFavoriteCount API 입니다.")
	@GetMapping()
	public BaseResponse<FavoriteCountResponseVo> readFavoriteCount(
		@RequestParam(value = "targetCode") String targetCode, @RequestParam(value = "type") String type) {
		FavoriteType favoriteType = FavoriteType.FEED;
		if (type.equals("피드")) {
			favoriteType = FavoriteType.FEED;
		} else if (type.equals("댓글")) {
			favoriteType = FavoriteType.COMMENT;
		}
		return new BaseResponse<>(favoriteWebVoMapper.toCountResponseVo(
			favoriteCountReadUseCase.readFavoriteCount(targetCode, favoriteType)));
	}
}
