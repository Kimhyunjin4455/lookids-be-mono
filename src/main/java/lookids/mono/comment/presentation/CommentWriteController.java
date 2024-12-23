package lookids.comment.comment.presentation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.comment.comment.application.CommentService;
import lookids.comment.comment.dto.in.CommentDeleteDto;
import lookids.comment.comment.dto.in.CommentRequestDto;
import lookids.comment.comment.dto.in.ReplyRequestDto;
import lookids.comment.comment.vo.in.CommentDeleteVo;
import lookids.comment.comment.vo.in.CommentRequestVo;
import lookids.comment.comment.vo.in.ReplyRequestVo;
import lookids.comment.common.entity.BaseResponse;
import lookids.comment.common.entity.BaseResponseStatus;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/write/comment")
public class CommentWriteController {

	private final CommentService commentService;   //final들어가면 변경 불가능!!!!!!!

	@Operation(summary = "createComment API", description = "createComment API 입니다.")
	@PostMapping()
	public BaseResponse<Void> createComment(@RequestHeader("uuid") String uuid,
		@RequestBody CommentRequestVo commentRequestVo) {
		commentService.createComment(CommentRequestDto.toDto(commentRequestVo, uuid));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "createComment API", description = "createComment API 입니다.")
	@PostMapping("/reply")
	public BaseResponse<Void> createReply(@RequestHeader("uuid") String uuid,
		@RequestBody ReplyRequestVo replyRequestVo) {
		commentService.createReply(ReplyRequestDto.toDto(replyRequestVo, uuid));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	/*
	@RequestParam url
	@PathParam
	@RequestBody
	@RequestHeader
*/
	@Operation(summary = "deleteComment API", description = "deleteComment API 입니다. 소프트 딜리트")
	@PutMapping("/delete")
	public BaseResponse<Void> deleteComment(@RequestHeader("uuid") String uuid,
		@RequestBody CommentDeleteVo commentDeleteVo) {
		commentService.deleteComment(CommentDeleteDto.toDto(commentDeleteVo, uuid));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

	@Operation(summary = "deleteComment API", description = "deleteComment API 입니다. 소프트 딜리트")
	@PutMapping("/reply/delete")
	public BaseResponse<Void> deleteReply(@RequestHeader("uuid") String uuid,
		@RequestBody CommentDeleteVo commentDeleteVo) {
		commentService.deleteReply(CommentDeleteDto.toDto(commentDeleteVo, uuid));
		return new BaseResponse<>(BaseResponseStatus.SUCCESS);
	}

}
