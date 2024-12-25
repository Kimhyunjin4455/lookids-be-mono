package lookids.mono.commentread.application.port.in;

import java.util.List;

import org.springframework.data.domain.Page;

import lookids.mono.commentread.application.port.dto.CommentCountResponseDto;
import lookids.mono.commentread.application.port.dto.CommentReadResponseDto;
import lookids.mono.commentread.application.port.dto.ReplyReadResponseDto;

public interface CommentReadUseCase {

	Page<CommentReadResponseDto> readCommentList(String feedCode, int page, int size);

	List<ReplyReadResponseDto> readReplyList(String parentCommentCode);

	CommentCountResponseDto readCommentCount(String feedCode);
}
