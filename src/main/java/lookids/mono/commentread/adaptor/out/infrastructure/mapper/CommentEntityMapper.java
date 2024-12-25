package lookids.mono.commentread.adaptor.out.infrastructure.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import lookids.mono.commentread.adaptor.out.infrastructure.entity.CommentReadEntity;
import lookids.mono.commentread.adaptor.out.infrastructure.entity.FeedEntity;
import lookids.mono.commentread.application.port.dto.CommentReadSaveDto;
import lookids.mono.commentread.application.port.dto.CommentReadUpdateDto;
import lookids.mono.commentread.domain.model.CommentForRead;
import lookids.mono.commentread.domain.model.FeedCount;

@Slf4j
@Component
public class CommentEntityMapper {

	public CommentReadEntity toEntity(CommentReadSaveDto commentReadSaveDto) {
		return CommentReadEntity.builder()
			.commentCode(commentReadSaveDto.getCommentCode())
			.feedCode(commentReadSaveDto.getFeedCode())
			.userUuid(commentReadSaveDto.getUserUuid())
			.content(commentReadSaveDto.getContent())
			.createdAt(commentReadSaveDto.getCreatedAt())
			.nickname(commentReadSaveDto.getNickname())
			.tag(commentReadSaveDto.getTag())
			.profileImg(commentReadSaveDto.getImage())
			.replyCount(commentReadSaveDto.getReplyCount())
			.replyList(commentReadSaveDto.getReplyForReadList())
			.build();
	}

	public CommentReadEntity toUpdateEntity(CommentReadUpdateDto commentReadUpdateDto) {
		return CommentReadEntity.builder()
			.id(commentReadUpdateDto.getId())
			.commentCode(commentReadUpdateDto.getCommentCode())
			.feedCode(commentReadUpdateDto.getFeedCode())
			.userUuid(commentReadUpdateDto.getUserUuid())
			.content(commentReadUpdateDto.getContent())
			.createdAt(commentReadUpdateDto.getCreatedAt())
			.nickname(commentReadUpdateDto.getNickname())
			.tag(commentReadUpdateDto.getTag())
			.profileImg(commentReadUpdateDto.getImage())
			.replyCount(commentReadUpdateDto.getReplyCount())
			.replyList(commentReadUpdateDto.getReplyForReadList())
			.build();
	}

	public CommentForRead toDomain(CommentReadEntity commentReadEntity) {
		return CommentForRead.builder()
			.id(commentReadEntity.getId())
			.commentCode(commentReadEntity.getCommentCode())
			.feedCode(commentReadEntity.getFeedCode())
			.userUuid(commentReadEntity.getUserUuid())
			.content(commentReadEntity.getContent())
			.createdAt(commentReadEntity.getCreatedAt())
			.nickname(commentReadEntity.getNickname())
			.tag(commentReadEntity.getTag())
			.image(commentReadEntity.getProfileImg())
			.replyCount(commentReadEntity.getReplyCount())
			.replyForReadList(commentReadEntity.getReplyList())
			.build();
	}

	public Page<CommentForRead> toDomainPage(Page<CommentReadEntity> commentReadEntities) {
		// Page의 내용을 변환
		return commentReadEntities.map(commentReadEntity -> CommentForRead.builder()
			.id(commentReadEntity.getId())
			.commentCode(commentReadEntity.getCommentCode())
			.feedCode(commentReadEntity.getFeedCode())
			.userUuid(commentReadEntity.getUserUuid())
			.content(commentReadEntity.getContent())
			.createdAt(commentReadEntity.getCreatedAt())
			.nickname(commentReadEntity.getNickname())
			.tag(commentReadEntity.getTag())
			.replyCount(commentReadEntity.getReplyCount())
			.image(commentReadEntity.getProfileImg())
			.build());
	}

	public FeedCount toCountDomain(FeedEntity feedEntity) {
		return FeedCount.builder()
			.feedCode(feedEntity.getId())
			.totalCommentCount(feedEntity.getTotalCommentCount())
			.build();
	}

	public FeedCount toNullCountDomain(String feedCode) {
		return FeedCount.builder().feedCode(feedCode).totalCommentCount(0).build();
	}
}
