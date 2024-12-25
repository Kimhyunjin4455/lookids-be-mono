package lookids.mono.commentread.application.port.in;

import lookids.mono.commentread.application.port.dto.CommentDeleteDto;
import lookids.mono.commentread.application.port.dto.ReplyDeleteDto;

public interface CommentDeleteUseCase {
	void deleteComment(CommentDeleteDto commentDeleteDto);

	void deleteReply(ReplyDeleteDto replyDeleteDto);

}
