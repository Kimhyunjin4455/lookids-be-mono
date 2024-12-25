package lookids.mono.favorite.presentation;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.common.entity.BaseResponse;
import lookids.mono.favorite.application.FavoriteService;
import lookids.mono.favorite.domain.FavoriteType;
import lookids.mono.favorite.dto.FavoriteResponseDto;
import lookids.mono.favorite.vo.FavoriteResponseVo;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/favorite-service/read/favorite")
@RestController
public class FavoriteReadController {

	private final FavoriteService favoriteService;

	@Operation(summary = "Read User Favorite List API", description = "사용자가 누른 Read Favorite List API 입니다", tags = {
		"Favorite"})
	@GetMapping("/user-favorite-list")
	public BaseResponse<List<FavoriteResponseVo>> readUserFavoriteList(@RequestHeader String uuid) {
		List<FavoriteResponseVo> favoriteResponseVoList = favoriteService.readUserFavoriteList(uuid)
			.stream()
			.map(FavoriteResponseDto::toVo)
			.toList();
		return new BaseResponse<>(favoriteResponseVoList);
	}

	@Operation(summary = "Read Feed Favorite List API", description = "피드/댓글에 누가 눌렀는지에 대한 Read Favorite List API 입니다.", tags = {
		"Favorite"})
	@GetMapping("/feed-favorite-list")
	public BaseResponse<List<FavoriteResponseVo>> readFeedFavoriteList(@RequestParam String targetCode,
		@RequestParam FavoriteType favoriteType, @RequestParam(defaultValue = "0") int page) {
		List<FavoriteResponseVo> favoriteRequestVoList = favoriteService.readFeedFavoriteList(targetCode, favoriteType,
			page, 20).stream().map(FavoriteResponseDto::toVo).toList();
		return new BaseResponse<>(favoriteRequestVoList);

	}

	@Operation(summary = "Read User Favorite Feed API", description = "하나의 피드/댓글에 대한 좋아요 여부 체크 API", tags = {
		"Favorite"})
	@GetMapping("feed")
	public BaseResponse<Boolean> readFavorite(@RequestHeader String uuid, @RequestParam String targetCode) {
		return new BaseResponse<>(favoriteService.readFavorite(uuid, targetCode));
	}

}
