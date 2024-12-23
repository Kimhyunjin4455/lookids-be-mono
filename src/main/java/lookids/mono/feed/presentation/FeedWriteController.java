package Lookids.Feed.feed.presentation;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Lookids.Feed.common.entity.BaseResponse;
import Lookids.Feed.common.entity.BaseResponseStatus;
import Lookids.Feed.feed.application.FeedService;
import Lookids.Feed.feed.dto.in.FeedRequestDto;
import Lookids.Feed.feed.vo.in.FeedRequestVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/write/feed")
public class FeedWriteController {

    private final FeedService feedService;

    @Operation(summary = "feed 등록 API", description = "feed 등록 API 입니다.", tags = {"Feed"})
    @PostMapping
    public BaseResponse<Void> createFeed(
            @RequestHeader String uuid,
            @RequestBody FeedRequestVo feedRequestVo) {
        feedService.createFeed(FeedRequestDto.toDto(feedRequestVo, uuid));
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(summary = "feed 삭제 API", description = "feed 삭제 API 입니다.", tags = {"Feed"})
    @DeleteMapping
    public BaseResponse<Void> deleteFeed(
        @RequestHeader String uuid,
        @RequestParam String feedCode) {
        feedService.deleteFeed(uuid, feedCode);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}
