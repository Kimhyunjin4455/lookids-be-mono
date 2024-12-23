package lookids.batch.feed.adaptor.in.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.batch.common.entity.BaseResponse;
import lookids.batch.feed.adaptor.in.web.mapper.FeedWebVoMapper;
import lookids.batch.feed.adaptor.in.web.out.FeedCountResponseVo;
import lookids.batch.feed.application.port.in.FeedCountReadUseCase;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/read/feed")
public class FeedReadController {
	private final FeedCountReadUseCase feedCountReadUseCase;
	private final FeedWebVoMapper feedWebVoMapper;

	@Operation(summary = "readFeedCount API", description = "readFeedCount API 입니다.")
	@GetMapping()
	public BaseResponse<FeedCountResponseVo> readFeedCount(@RequestParam(value = "uuid") String feedCode) {
		return new BaseResponse<>(feedWebVoMapper.toCountResponseVo(feedCountReadUseCase.readFeedCount(feedCode)));
	}
}
