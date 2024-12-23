package lookids.comment.comment.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.comment.comment.application.CommentService;
import lookids.comment.common.dto.PageResponseDto;
import lookids.comment.common.entity.BaseResponse;
import lookids.comment.common.vo.PageResponseVo;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/read/comment")
public class CommentReadController {

	private final CommentService commentService;   //final들어가면 변경 불가능!!!!!!!

	@Operation(summary = "readCommentList API", description = "readCommentList API 입니다.")
	@GetMapping()
	public BaseResponse<PageResponseVo> readCommentList(@RequestParam(value = "feedCode") String feedCode,
		@RequestParam(value = "page", defaultValue = "0") int page,
		@RequestParam(value = "size", defaultValue = "20") int size) {  //한번에 몇개를 가지고 올지..20개를 갖고 올거.
		log.info("요청받음 : {}", feedCode);
		// 조회할 피드코드를 서비스 함수에 파라미터로 넘겨줌

		PageResponseDto pageResponseDto = commentService.getCommentList(feedCode, page, size);

		return new BaseResponse<>(pageResponseDto.toVo());
	}

	@Operation(summary = "readCommentList API", description = "readReplyList API 입니다.")
	@GetMapping("/reply")
	public BaseResponse<PageResponseVo> readReplyList(@RequestParam(value = "commentCode") String commentCode,
		@RequestParam(value = "page", defaultValue = "0") int page,
		@RequestParam(value = "size", defaultValue = "50") int size) {  //한번에 몇개를 가지고 올지..20개를 갖고 올거.
		log.info("요청받음 : {}", commentCode);
		// 조회할 부모 댓글코드를 서비스 함수에 파라미터로 넘겨줌

		PageResponseDto pageResponseDto = commentService.readReplyList(commentCode, page, size);

		return new BaseResponse<>(pageResponseDto.toVo());
	}
}
