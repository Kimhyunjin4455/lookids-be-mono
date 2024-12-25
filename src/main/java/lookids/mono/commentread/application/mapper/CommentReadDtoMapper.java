package lookids.mono.commentread.application.mapper;

import java.util.List;

import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import lookids.mono.commentread.adaptor.in.kafka.event.CommentEvent;
import lookids.mono.commentread.adaptor.in.kafka.event.NicknameEvent;
import lookids.mono.commentread.adaptor.in.kafka.event.ProfileImageEvent;
import lookids.mono.commentread.adaptor.in.kafka.event.ReplyEvent;
import lookids.mono.commentread.adaptor.in.kafka.event.UserProfileEvent;
import lookids.mono.commentread.application.port.dto.CommentCountResponseDto;
import lookids.mono.commentread.application.port.dto.CommentCreateEventDto;
import lookids.mono.commentread.application.port.dto.CommentDeleteDto;
import lookids.mono.commentread.application.port.dto.CommentDeleteSaveDto;
import lookids.mono.commentread.application.port.dto.CommentReadResponseDto;
import lookids.mono.commentread.application.port.dto.CommentReadSaveDto;
import lookids.mono.commentread.application.port.dto.CommentReadUpdateDto;
import lookids.mono.commentread.application.port.dto.ReplyCreateEventDto;
import lookids.mono.commentread.application.port.dto.ReplyDeleteDto;
import lookids.mono.commentread.application.port.dto.ReplyReadResponseDto;
import lookids.mono.commentread.application.port.dto.UserProfileImageDto;
import lookids.mono.commentread.application.port.dto.UserProfileNicknameDto;
import lookids.mono.commentread.application.port.dto.UserProfileUpdateSaveDto;
import lookids.mono.commentread.domain.model.CommentForRead;
import lookids.mono.commentread.domain.model.FeedCount;
import lookids.mono.commentread.domain.model.ReplyForRead;

@Component
public class CommentReadDtoMapper {
	public CommentReadSaveDto toCommentReadSaveDto(CommentForRead commentForRead) {
		return CommentReadSaveDto.builder()
			.commentCode(commentForRead.getCommentCode())
			.feedCode(commentForRead.getFeedCode())
			.content(commentForRead.getContent())
			.userUuid(commentForRead.getUserUuid())
			.createdAt(commentForRead.getCreatedAt())
			.nickname(commentForRead.getNickname())
			.tag(commentForRead.getTag())
			.image(commentForRead.getImage())
			.replyCount(commentForRead.getReplyCount())
			.replyForReadList(commentForRead.getReplyForReadList())
			.build();
	}

	public CommentReadUpdateDto toCommentReadUpdateDto(CommentForRead commentForRead, Integer size) {
		return CommentReadUpdateDto.builder()
			.id(commentForRead.getId())
			.commentCode(commentForRead.getCommentCode())
			.feedCode(commentForRead.getFeedCode())
			.content(commentForRead.getContent())
			.userUuid(commentForRead.getUserUuid())
			.createdAt(commentForRead.getCreatedAt())
			.nickname(commentForRead.getNickname())
			.tag(commentForRead.getTag())
			.image(commentForRead.getImage())
			.replyCount(size)
			.replyForReadList(commentForRead.getReplyForReadList())
			.build();
	}

	public CommentReadSaveDto toReplyReadSaveDto(CommentForRead commentForRead, List<ReplyForRead> replyForReadList) {
		return CommentReadSaveDto.builder()
			.commentCode(commentForRead.getCommentCode())
			.feedCode(commentForRead.getFeedCode())
			.content(commentForRead.getContent())
			.userUuid(commentForRead.getUserUuid())
			.createdAt(commentForRead.getCreatedAt())
			.nickname(commentForRead.getNickname())
			.tag(commentForRead.getTag())
			.image(commentForRead.getImage())
			.replyForReadList(replyForReadList)
			.build();
	}

	public CommentReadResponseDto toCommentReadResponseDto(CommentForRead commentForRead) {
		return CommentReadResponseDto.builder()
			.commentCode(commentForRead.getCommentCode())
			.content(commentForRead.getContent())
			.createdAt(commentForRead.getCreatedAt())
			.userUuid(commentForRead.getUserUuid())
			.nickname(commentForRead.getNickname())
			.tag(commentForRead.getTag())
			.image(commentForRead.getImage())
			.replyCount(commentForRead.getReplyCount())
			.build();
	}

	public ReplyReadResponseDto toReplyReadResponseDto(ReplyForRead replyForRead) {
		return ReplyReadResponseDto.builder()
			.commentCode(replyForRead.getCommentCode())
			.content(replyForRead.getContent())
			.createdAt(replyForRead.getCreatedAt())
			.userUuid(replyForRead.getUserUuid())
			.nickname(replyForRead.getNickname())
			.tag(replyForRead.getTag())
			.image(replyForRead.getProfileImg())
			.build();
	}

	public CommentCreateEventDto toCommentCreateEventDto(CommentEvent commentEvent, UserProfileEvent userProfileEvent) {
		return CommentCreateEventDto.builder()
			.commentCode(commentEvent.getCommentCode())
			.feedCode(commentEvent.getFeedCode())
			.userUuid(commentEvent.getUuid())
			.content(commentEvent.getContent())
			.createdAt(commentEvent.getCreatedAt())
			.nickname(userProfileEvent.getNickname())
			.tag(userProfileEvent.getTag())
			.image(userProfileEvent.getImage())
			.build();
	}

	public ReplyCreateEventDto toReplyCreateEventDto(ReplyEvent replyEvent, UserProfileEvent userProfileEvent) {
		return ReplyCreateEventDto.builder()
			.commentCode(replyEvent.getCommentCode())
			.parentCommentCode(replyEvent.getParentCommentCode())
			.userUuid(replyEvent.getUuid())
			.content(replyEvent.getContent())
			.createdAt(replyEvent.getCreatedAt())
			.nickname(userProfileEvent.getNickname())
			.tag(userProfileEvent.getTag())
			.image(userProfileEvent.getImage())
			.build();
	}

	public UserProfileNicknameDto toNicknameDto(NicknameEvent nicknameEvent) {
		return UserProfileNicknameDto.builder()
			.userUuid(nicknameEvent.getUuid())
			.nickname(nicknameEvent.getNickname())
			.tag(nicknameEvent.getTag())
			.build();
	}

	public UserProfileImageDto toProfileImageDto(ProfileImageEvent profileImageEvent) {
		return UserProfileImageDto.builder()
			.userUuid(profileImageEvent.getUuid())
			.image(profileImageEvent.getImage())
			.build();
	}

	public UserProfileUpdateSaveDto toProfileUpdateSaveDto(String uuid, Update update) {
		return UserProfileUpdateSaveDto.builder().userUuid(uuid).update(update).build();
	}

	public CommentDeleteDto toCommentDeleteDto(CommentEvent commentEvent) {
		return CommentDeleteDto.builder()
			.commentCode(commentEvent.getCommentCode())
			.feedCode(commentEvent.getFeedCode())
			.build();
	}

	public ReplyDeleteDto toReplyDeleteDto(ReplyEvent replyEvent) {
		return ReplyDeleteDto.builder()
			.commentCode(replyEvent.getCommentCode())
			.parentCommentCode(replyEvent.getParentCommentCode())
			.build();
	}

	public CommentDeleteSaveDto toCommentDeleteSaveDto(CommentDeleteDto commentDeleteDto, Integer totalToDelete) {
		return CommentDeleteSaveDto.builder()
			.commentCode(commentDeleteDto.getCommentCode())
			.feedCode(commentDeleteDto.getFeedCode())
			.totalToDelete(totalToDelete)
			.build();
	}

	public CommentCountResponseDto toCommentCountResponseDto(FeedCount feedCount) {
		return CommentCountResponseDto.builder()
			.feedCode(feedCount.getFeedCode())
			.commentCount(feedCount.getTotalCommentCount())
			.build();
	}
}
