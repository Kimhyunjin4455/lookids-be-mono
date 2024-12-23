package lookids.batch.comment.adaptor.out.infrastructure.mysql;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lookids.batch.comment.adaptor.out.infrastructure.entity.CommentLogEntity;

@Repository
public interface CommentLogRepository extends JpaRepository<CommentLogEntity, Long> {
	Page<CommentLogEntity> findAllByFeedCode(String feedCode, Pageable pageable);

	List<CommentLogEntity> findAllByParentCommentCode(String commentCode);

	Optional<CommentLogEntity> findByCommentCode(String commentCode);

	Boolean existsByCommentCode(String commentCode);
}