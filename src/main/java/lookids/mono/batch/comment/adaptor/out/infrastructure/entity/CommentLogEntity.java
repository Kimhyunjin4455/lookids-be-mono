package lookids.batch.comment.adaptor.out.infrastructure.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class CommentLogEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String commentCode;

	@Column(nullable = false, length = 50)
	private String feedCode;

	@Column(nullable = false, length = 50)
	private String uuid;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@Column(nullable = true, length = 50)
	private String parentCommentCode;

	@Column(nullable = false)
	private String commentType;

	@Column(nullable = false)
	private String logType;

	@Builder
	public CommentLogEntity(Long id, String feedCode, String commentCode, String uuid, String logType,
		LocalDateTime createdAt, String parentCommentCode, String commentType) {
		this.id = id;
		this.commentCode = commentCode;
		this.feedCode = feedCode;
		this.uuid = uuid;
		this.createdAt = createdAt;
		this.parentCommentCode = parentCommentCode;
		this.commentType = commentType;
		this.logType = logType;
	}
}