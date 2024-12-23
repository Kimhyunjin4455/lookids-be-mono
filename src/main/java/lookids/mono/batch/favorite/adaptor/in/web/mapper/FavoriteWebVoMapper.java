package lookids.batch.favorite.adaptor.in.web.mapper;

import org.springframework.stereotype.Component;

import lookids.batch.favorite.adaptor.in.web.out.FavoriteCountResponseVo;
import lookids.batch.favorite.application.port.dto.FavoriteCountResponseDto;

@Component
public class FavoriteWebVoMapper {

	public FavoriteCountResponseVo toCountResponseVo(FavoriteCountResponseDto favoriteCountResponseDto) {
		return FavoriteCountResponseVo.builder().count(favoriteCountResponseDto.getCount()).build();
	}
}
