package lookids.batch.comment.adaptor.out.infrastructure.mapper;

import org.springframework.stereotype.Component;

import lookids.batch.comment.adaptor.out.infrastructure.entity.CommentLogEntity;
import lookids.batch.comment.application.port.dto.CommentLogSaveDto;

@Component
public class CommentEntityMapper {

	public CommentLogEntity toCommentLogEntity(CommentLogSaveDto commentLogSaveDto) {
		return CommentLogEntity.builder()
			.uuid(commentLogSaveDto.getUuid())
			.feedCode(commentLogSaveDto.getFeedCode())
			.commentCode(commentLogSaveDto.getCommentCode())
			.createdAt(commentLogSaveDto.getCreatedAt())
			.parentCommentCode(commentLogSaveDto.getParentCommentCode())
			.commentType(commentLogSaveDto.getCommentType())
			.logType(commentLogSaveDto.getLogType())
			.build();
	}

}
