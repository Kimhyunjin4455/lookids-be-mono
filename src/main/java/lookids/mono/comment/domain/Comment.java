package lookids.comment.comment.domain;

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
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String commentCode;

	@Column(nullable = false, length = 50)
	private String feedCode;

	@Column(nullable = false, length = 50)
	private String userUuid;

	@Column(nullable = false, columnDefinition = "TEXT", length = 500)
	private String content;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@Column(nullable = true, length = 50)
	private String parentCommentCode;

	// 삭제됐는지를 판단하는 상태값 컬럼 true or false
	@Column(nullable = false)
	private boolean commentStatus;

	@Builder
	public Comment(Long id, String feedCode, String commentCode, String userUuid, String content,
		String parentCommentCode, Boolean commentStatus) {
		this.id = id;
		this.commentCode = commentCode;
		this.feedCode = feedCode;
		this.userUuid = userUuid;
		this.content = content;
		this.createdAt = LocalDateTime.now();
		this.parentCommentCode = parentCommentCode;
		this.commentStatus = commentStatus;
	}
}