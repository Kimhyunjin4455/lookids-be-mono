package lookids.mono.commentread.application.port.in;

import lookids.mono.commentread.application.port.dto.CommentCreateEventDto;
import lookids.mono.commentread.application.port.dto.ReplyCreateEventDto;

public interface CommentReadCreateUseCase {

	void createCommentRead(CommentCreateEventDto commentCreateEventDto);

	void createReplyRead(ReplyCreateEventDto replyCreateEventDto);
}
