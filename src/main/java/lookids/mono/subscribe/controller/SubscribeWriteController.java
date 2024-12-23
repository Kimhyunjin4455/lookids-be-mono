package lookids.subscribe.subscribe.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lookids.subscribe.common.entity.BaseResponse;
import lookids.subscribe.subscribe.dto.in.SubscribeRequestDto;
import lookids.subscribe.subscribe.service.SubscribeService;
import lookids.subscribe.subscribe.vo.in.SubscribeRequestVo;

@RequiredArgsConstructor
@RestController
@RequestMapping("/write/subscribe")
public class SubscribeWriteController {
	private final SubscribeService subscribeService;

	@Operation(summary = "게시글 알림 신청 API",
		description = """
        <p>이 API는 특정 게시글에 대한 알림을 신청하는 기능을 제공합니다.</p>
        <p><strong>헤더</strong>에 포함된 <code>subscriberUuid</code>는 요청자의 고유 ID이며, 본인의 UUID를 전달해야 합니다.</p>
        <p><strong>바디</strong>에 포함된 데이터는 알림을 신청할 게시글 정보 및 알림 설정 값을 담고 있습니다.</p>
        <ul>
            <li><code>uuid</code>: 알림 신청자의 UUID (헤더)</li>
            <li><code>authorUuid</code>: 알림을 신청할 게시글 작성자의 UUID (경로 파라미터)</li>
        </ul>""",
		tags = {"Subscribe"})
	@PostMapping
	public BaseResponse<Void> createSubscribe(
		@RequestHeader String uuid,
		@RequestBody SubscribeRequestVo subscribeRequestVo) {
		subscribeService.createSubscribe(SubscribeRequestDto.toDto(uuid, subscribeRequestVo));
		return new BaseResponse<>();
	}

	@Operation(summary = "게시글 알림 신청 취소 API", description = "해당 게시글의 알림을 취소합니다.", tags = {"Subscribe"})
	@DeleteMapping
	public BaseResponse<Void> deleteSubscribe(
		@RequestHeader String uuid,
		@RequestBody SubscribeRequestVo subscribeRequestVo) {
		subscribeService.deleteSubscribe(SubscribeRequestDto.toDto(uuid, subscribeRequestVo));
		return new BaseResponse<>();
	}



}
