package lookids.mono.commentread.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lookids.mono.commentread.application.mapper.CommentReadDtoMapper;
import lookids.mono.commentread.application.port.dto.CommentCountResponseDto;
import lookids.mono.commentread.application.port.dto.CommentReadResponseDto;
import lookids.mono.commentread.application.port.dto.ReplyReadResponseDto;
import lookids.mono.commentread.application.port.in.CommentReadUseCase;
import lookids.mono.commentread.application.port.out.CommentRepositoryPort;
import lookids.mono.commentread.domain.model.CommentForRead;
import lookids.mono.commentread.domain.model.ReplyForRead;

@RequiredArgsConstructor
@Service
public class CommentReadService implements CommentReadUseCase {
	private final CommentRepositoryPort commentRepositoryPort;
	private final CommentReadDtoMapper commentReadDtoMapper;

	@Override
	public Page<CommentReadResponseDto> readCommentList(String feedCode, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
		Page<CommentForRead> commentList = commentRepositoryPort.readCommentList(feedCode, pageable);

		// List<CommentReadResponseDto> responseDtoList = commentList.stream()
		// 	.map(commentReadDtoMapper::toCommentReadResponseDto) // 메서드 참조 사용
		// 	.toList();
		//
		// return PageResponseDto.toDto(page, commentList.getTotalPages(), commentList.hasNext(), responseDtoList);
		return commentList.map(commentReadDtoMapper::toCommentReadResponseDto);
	}

	@Override
	public List<ReplyReadResponseDto> readReplyList(String parentCommentCode) {
		List<ReplyForRead> replyForReadList = commentRepositoryPort.readComment(parentCommentCode)
			.getReplyForReadList();
		return replyForReadList.stream().map(commentReadDtoMapper::toReplyReadResponseDto).toList();
	}

	@Override
	public CommentCountResponseDto readCommentCount(String feedCode) {
		return commentReadDtoMapper.toCommentCountResponseDto(commentRepositoryPort.readCommentCount(feedCode));
	}
}
