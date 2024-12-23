package lookids.batch.comment.application.mapper;

import org.springframework.stereotype.Component;

import lookids.batch.comment.adaptor.in.kafka.event.CommentEvent;
import lookids.batch.comment.adaptor.in.kafka.event.ReplyEvent;
import lookids.batch.comment.application.port.dto.CommentCreateEventDto;
import lookids.batch.comment.application.port.dto.CommentLogSaveDto;
import lookids.batch.comment.application.port.dto.ReplyCreateEventDto;
import lookids.batch.comment.domain.model.CommentLog;

@Component
public class CommentDtoMapper {

	public CommentCreateEventDto toCommentCreateEventDto(CommentEvent commentEvent) {
		return CommentCreateEventDto.builder()
			.commentCode(commentEvent.getCommentCode())
			.feedCode(commentEvent.getFeedCode())
			.uuid(commentEvent.getUuid())
			.createdAt(commentEvent.getCreatedAt())
			.build();
	}

	public CommentLogSaveDto toCommentLogSaveDto(CommentLog commentLog) {
		return CommentLogSaveDto.builder()
			.commentCode(commentLog.getCommentCode())
			.feedCode(commentLog.getFeedCode())
			.uuid(commentLog.getUuid())
			.createdAt(commentLog.getCreatedAt())
			.parentCommentCode(commentLog.getParentCommentCode())
			.logType(commentLog.getLogType())
			.commentType(commentLog.getCommentType())
			.build();
	}

	public ReplyCreateEventDto toReplyCreateEventDto(ReplyEvent replyEvent) {
		return ReplyCreateEventDto.builder()
			.commentCode(replyEvent.getCommentCode())
			.feedCode(replyEvent.getFeedCode())
			.uuid(replyEvent.getUuid())
			.createdAt(replyEvent.getCreatedAt())
			.parentCommentCode(replyEvent.getParentCommentCode())
			.build();
	}
}
