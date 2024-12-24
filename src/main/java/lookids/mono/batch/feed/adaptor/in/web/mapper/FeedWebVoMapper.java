package lookids.mono.batch.feed.adaptor.in.web.mapper;

import org.springframework.stereotype.Component;

import lookids.mono.batch.feed.adaptor.in.web.out.FeedCountResponseVo;
import lookids.mono.batch.feed.application.port.dto.FeedCountResponseDto;

@Component
public class FeedWebVoMapper {

	public FeedCountResponseVo toCountResponseVo(FeedCountResponseDto feedCountResponseDto) {
		return FeedCountResponseVo.builder().count(feedCountResponseDto.getCount()).build();
	}

}
