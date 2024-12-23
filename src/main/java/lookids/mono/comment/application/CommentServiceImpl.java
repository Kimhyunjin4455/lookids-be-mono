package lookids.mono.comment.application;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.comment.domain.Comment;
import lookids.mono.comment.dto.in.CommentDeleteDto;
import lookids.mono.comment.dto.in.CommentRequestDto;
import lookids.mono.comment.dto.in.ReplyRequestDto;
import lookids.mono.comment.dto.out.CommentResponseDto;
import lookids.mono.comment.infrastructure.CommentRepository;
import lookids.mono.comment.vo.out.CommentKafkaVo;
import lookids.mono.comment.vo.out.ReplyKafkaVo;
import lookids.mono.common.entity.BaseResponseStatus;
import lookids.mono.common.exception.BaseException;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor //생성자 주입(?)
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;
	private final KafkaTemplate<String, CommentKafkaVo> commentkafkaTemplate;
	private final KafkaTemplate<String, ReplyKafkaVo> replykafkaTemplate;

	@Value("${comment.create}")
	private String commentCreateTopic;

	@Value("${comment.delete}")
	private String commentDeleteTopic;

	@Value("${reply.create}")
	private String replyCreateTopic;

	@Value("${reply.delete}")
	private String replyDeleteTopic;

	@Override
	public void createComment(CommentRequestDto commentRequestDto) {

		Comment comment = commentRepository.save(commentRequestDto.toEntity(generateUniqueCommentCode()));
		//save() 메서드는 엔티티의 삽입(insert)과 수정(update)을 처리하는 중요한 메서드
		commentkafkaTemplate.send(commentCreateTopic,
			CommentResponseDto.toDto(comment).toCommentKafkaVo(commentRequestDto.getFeedUuid()));
	}

	@Override
	public void createReply(ReplyRequestDto replyRequestDto) {

		Comment comment = commentRepository.save(replyRequestDto.toEntity(generateUniqueCommentCode()));
		//save() 메서드는 엔티티의 삽입(insert)과 수정(update)을 처리하는 중요한 메서드
		log.info("${reply.create}");
		replykafkaTemplate.send(replyCreateTopic,
			CommentResponseDto.toDto(comment).toReplyKafkaVo(replyRequestDto.getFeedUuid()));
	}

	@Override
	public void deleteComment(CommentDeleteDto commentDeleteDto) {
		Comment comment = commentRepository.findByCommentCodeAndUserUuidAndCommentStatus(
				commentDeleteDto.getCommentCode(), commentDeleteDto.getUserUuid(), true)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		commentkafkaTemplate.send(commentDeleteTopic, CommentResponseDto.toDto(comment).toCommentDeleteKafkaVo());
		commentRepository.save(commentDeleteDto.toEntity(comment));
	}

	@Override
	public void deleteReply(CommentDeleteDto commentDeleteDto) {
		Comment comment = commentRepository.findByCommentCodeAndUserUuidAndCommentStatus(
				commentDeleteDto.getCommentCode(), commentDeleteDto.getUserUuid(), true)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		replykafkaTemplate.send(replyDeleteTopic, CommentResponseDto.toDto(comment).toReplyDeleteKafkaVo());
		commentRepository.save(commentDeleteDto.toEntity(comment));
	}

	private String generateUniqueCommentCode() {
		int maxAttempts = 5;  // 최대 시도 횟수
		int attempt = 0;
		String commentCode;

		do {
			commentCode = UUID.randomUUID().toString();
			attempt++;
			if (attempt >= maxAttempts) {
				throw new BaseException(BaseResponseStatus.DUPLICATED_COMMENT);
			}
		} while (commentRepository.existsByCommentCode(commentCode));

		return commentCode;
	}
}
