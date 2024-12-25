package lookids.mono.commentread.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lookids.mono.commentread.application.port.dto.CommentDeleteSaveDto;
import lookids.mono.commentread.application.port.dto.CommentReadSaveDto;
import lookids.mono.commentread.application.port.dto.CommentReadUpdateDto;
import lookids.mono.commentread.application.port.dto.ReplyDeleteDto;
import lookids.mono.commentread.application.port.dto.UserProfileUpdateSaveDto;
import lookids.mono.commentread.domain.model.CommentForRead;
import lookids.mono.commentread.domain.model.FeedCount;

public interface CommentRepositoryPort {

	void createComment(CommentReadSaveDto commentReadSaveDto);

	void updateComment(CommentReadUpdateDto commentReadUpdateDto);

	void updateUserProfile(UserProfileUpdateSaveDto userProfileUpdateSaveDto);

	Page<CommentForRead> readCommentList(String feedCode, Pageable pageable);

	CommentForRead readComment(String commentCode);

	void deleteComment(CommentDeleteSaveDto commentDeleteSaveDto);

	void deleteReply(ReplyDeleteDto replyDeleteDto);

	String getFeedCodeByComment(String commentCode);

	void updateFeedCommentCount(String feedCode, int change);

	FeedCount readCommentCount(String feedCode);
}