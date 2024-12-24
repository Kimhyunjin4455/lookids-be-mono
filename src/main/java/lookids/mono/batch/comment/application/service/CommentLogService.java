package lookids.mono.batch.comment.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.batch.comment.application.mapper.CommentDtoMapper;
import lookids.mono.batch.comment.application.port.dto.CommentCreateEventDto;
import lookids.mono.batch.comment.application.port.dto.ReplyCreateEventDto;
import lookids.mono.batch.comment.application.port.in.CommentLogUseCase;
import lookids.mono.batch.comment.application.port.out.CommentRepositoryPort;
import lookids.mono.batch.comment.domain.model.CommentLog;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentLogService implements CommentLogUseCase {

	private final CommentDtoMapper commentDtoMapper;
	private final CommentRepositoryPort commentRepositoryPort;

	@Override
	public void commentCreateLog(List<CommentCreateEventDto> commentCreateEventDtoList) {
		List<CommentLog> commentLogList = commentCreateEventDtoList.stream()
			.map(commentCreateEventDto -> CommentLog.builder()
				.commentCode(commentCreateEventDto.getCommentCode())
				.feedCode(commentCreateEventDto.getFeedCode())
				.uuid(commentCreateEventDto.getUuid())
				.createdAt(commentCreateEventDto.getCreatedAt())
				.logType("create")
				.commentType("comment")
				.build())
			.toList();
		commentRepositoryPort.createLog(commentLogList.stream().map(commentDtoMapper::toCommentLogSaveDto).toList());
	}

	@Override
	public void commentDeleteLog(List<CommentCreateEventDto> commentCreateEventDtoList) {
		List<CommentLog> commentLogList = commentCreateEventDtoList.stream()
			.map(commentCreateEventDto -> CommentLog.builder()
				.commentCode(commentCreateEventDto.getCommentCode())
				.feedCode(commentCreateEventDto.getFeedCode())
				.uuid(commentCreateEventDto.getUuid())
				.createdAt(commentCreateEventDto.getCreatedAt())
				.logType("delete")
				.commentType("comment")
				.build())
			.toList();
		commentRepositoryPort.createLog(commentLogList.stream().map(commentDtoMapper::toCommentLogSaveDto).toList());
	}

	@Override
	public void replyCreateLog(List<ReplyCreateEventDto> replyCreateEventDtoList) {
		List<CommentLog> commentLogList = replyCreateEventDtoList.stream()
			.map(replyCreateEventDto -> CommentLog.builder()
				.commentCode(replyCreateEventDto.getCommentCode())
				.feedCode(replyCreateEventDto.getFeedCode())
				.uuid(replyCreateEventDto.getUuid())
				.createdAt(replyCreateEventDto.getCreatedAt())
				.parentCommentCode(replyCreateEventDto.getParentCommentCode())
				.logType("create")
				.commentType("reply")
				.build())
			.toList();
		commentRepositoryPort.createLog(commentLogList.stream().map(commentDtoMapper::toCommentLogSaveDto).toList());
	}

	@Override
	public void replyDeleteLog(List<ReplyCreateEventDto> replyCreateEventDtoList) {
		List<CommentLog> commentLogList = replyCreateEventDtoList.stream()
			.map(replyCreateEventDto -> CommentLog.builder()
				.commentCode(replyCreateEventDto.getCommentCode())
				.feedCode(replyCreateEventDto.getFeedCode())
				.uuid(replyCreateEventDto.getUuid())
				.createdAt(replyCreateEventDto.getCreatedAt())
				.parentCommentCode(replyCreateEventDto.getParentCommentCode())
				.logType("delete")
				.commentType("reply")
				.build())
			.toList();
		commentRepositoryPort.createLog(commentLogList.stream().map(commentDtoMapper::toCommentLogSaveDto).toList());
	}
}