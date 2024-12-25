package lookids.mono.commentread.adaptor.in.web.mapper;

import org.springframework.stereotype.Component;

import lookids.mono.commentread.adaptor.in.web.vo.out.CommentCountResponseVo;
import lookids.mono.commentread.adaptor.in.web.vo.out.CommentReadResponseVo;
import lookids.mono.commentread.adaptor.in.web.vo.out.ReplyReadResponseVo;
import lookids.mono.commentread.application.port.dto.CommentCountResponseDto;
import lookids.mono.commentread.application.port.dto.CommentReadResponseDto;
import lookids.mono.commentread.application.port.dto.ReplyReadResponseDto;

@Component
public class WebVoMapper {

	public CommentReadResponseVo toCommentReadResponseVo(CommentReadResponseDto commentReadResponseDto) {
		return CommentReadResponseVo.builder()
			.commentCode(commentReadResponseDto.getCommentCode())
			.content(commentReadResponseDto.getContent())
			.createdAt(commentReadResponseDto.getCreatedAt())
			.uuid(commentReadResponseDto.getUserUuid())
			.nickname(commentReadResponseDto.getNickname())
			.tag(commentReadResponseDto.getTag())
			.image(commentReadResponseDto.getImage())
			.replyCount(commentReadResponseDto.getReplyCount())
			.build();
	}

	public ReplyReadResponseVo toReplyReadResponseVo(ReplyReadResponseDto replyReadResponseDto) {
		return ReplyReadResponseVo.builder()
			.commentCode(replyReadResponseDto.getCommentCode())
			.content(replyReadResponseDto.getContent())
			.createdAt(replyReadResponseDto.getCreatedAt())
			.uuid(replyReadResponseDto.getUserUuid())
			.nickname(replyReadResponseDto.getNickname())
			.tag(replyReadResponseDto.getTag())
			.image(replyReadResponseDto.getImage())
			.build();
	}

	public CommentCountResponseVo toCommentCountResponseVo(CommentCountResponseDto commentCountResponseDto) {
		return CommentCountResponseVo.builder().commentCount(commentCountResponseDto.getCommentCount()).build();
	}
}
