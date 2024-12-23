package lookids.comment.comment.infrastructure;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import lookids.comment.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	Page<Comment> findAllByFeedCodeAndCommentStatus(String feedCode, boolean commentStatus, Pageable pageable);

	Page<Comment> findAllByParentCommentCodeAndCommentStatus(String parentCommentCode, boolean commentStatus,
		Pageable pageable);

	Optional<Comment> findByCommentCodeAndUserUuidAndCommentStatus(String commentCode, String userUuid,
		boolean commentStatus);

	Optional<Comment> findByCommentCode(String commentCode);

	Boolean existsByCommentCode(String commentCode);
}