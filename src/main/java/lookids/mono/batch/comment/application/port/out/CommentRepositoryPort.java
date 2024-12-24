package lookids.mono.batch.comment.application.port.out;

import java.util.List;

import lookids.mono.batch.comment.application.port.dto.CommentLogSaveDto;

public interface CommentRepositoryPort {
	void createLog(List<CommentLogSaveDto> commentLogSaveDtoList);
}
