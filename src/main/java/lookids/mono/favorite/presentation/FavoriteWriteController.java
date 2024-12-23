package lookids.favorite.favorite.presentation;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.favorite.common.entity.BaseResponse;
import lookids.favorite.common.entity.BaseResponseStatus;
import lookids.favorite.favorite.application.FavoriteService;
import lookids.favorite.favorite.dto.FavoriteRequestDto;
import lookids.favorite.favorite.vo.FavoriteRequestVo;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/write/favorite")
@RestController
public class FavoriteWriteController {

	private final FavoriteService favoriteService;

	@Operation(summary = "Update Favorite API", description = "Update Favorite API 입니다", tags = {"Favorite"})
	@PutMapping()
	public BaseResponse<Void> updateFavorite(
		@RequestHeader String uuid,
		@RequestBody FavoriteRequestVo favoriteRequestVo
	) {
		favoriteService.updateFavorite(FavoriteRequestDto.toDto(favoriteRequestVo, uuid));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

}
