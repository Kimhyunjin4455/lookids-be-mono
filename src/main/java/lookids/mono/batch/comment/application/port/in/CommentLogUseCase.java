package lookids.mono.batch.comment.application.port.in;

import java.util.List;

import lookids.mono.batch.comment.application.port.dto.CommentCreateEventDto;
import lookids.mono.batch.comment.application.port.dto.ReplyCreateEventDto;

public interface CommentLogUseCase {
	void commentCreateLog(List<CommentCreateEventDto> commentCreateEventDtoList);

	void commentDeleteLog(List<CommentCreateEventDto> commentCreateEventDtoList);

	void replyCreateLog(List<ReplyCreateEventDto> replyCreateEventDtoList);

	void replyDeleteLog(List<ReplyCreateEventDto> replyCreateEventDtoList);

}
