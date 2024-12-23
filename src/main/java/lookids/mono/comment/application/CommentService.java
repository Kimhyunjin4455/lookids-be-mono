package lookids.comment.comment.application;

import lookids.comment.comment.dto.in.CommentDeleteDto;
import lookids.comment.comment.dto.in.CommentRequestDto;
import lookids.comment.comment.dto.in.ReplyRequestDto;
import lookids.comment.common.dto.PageResponseDto;

/**
 * 댓글을 관리하는 서비스 인터페이스입니다.
 */
public interface CommentService {

	/**
	 * 새로운 댓글을 생성합니다.
	 *
	 * @param commentRequestDto 댓글 세부 정보를 포함하는 데이터 전송 객체
	 */
	void createComment(CommentRequestDto commentRequestDto);

	void createReply(ReplyRequestDto replyRequestDto);

	/**
	 * ID로 댓글을 조회합니다.
	 *
	 * @param id 댓글의 ID
	 * @return 댓글 응답 데이터 전송 객체
	 */
	// CommentResponseDto getCommentById(Long id);

	/**
	 * 피드 코드로 댓글 목록을 조회합니다.
	 *
	 * @param feedCode 피드 코드
	 * @return 댓글 응답 데이터 전송 객체 목록
	 */
	PageResponseDto getCommentList(String feedCode, int page, int size);

	PageResponseDto readReplyList(String parentCommentCode, int page, int size);

	/**
	 * 댓글을 삭제합니다.
	 *
	 * @param commentCode 댓글 코드
	 */
	void deleteComment(CommentDeleteDto commentDeleteDto);

	void deleteReply(CommentDeleteDto commentDeleteDto);

}
