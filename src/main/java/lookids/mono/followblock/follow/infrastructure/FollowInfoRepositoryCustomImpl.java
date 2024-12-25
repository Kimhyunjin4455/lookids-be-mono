package lookids.mono.followblock.follow.infrastructure;

import static lookids.mono.followblock.follow.domain.QFollowInfo.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import lookids.mono.followblock.follow.domain.FollowInfo;
import lookids.mono.followblock.follow.dto.out.FollowInfoResponseDto;

@Repository
@RequiredArgsConstructor
public class FollowInfoRepositoryCustomImpl implements FollowInfoRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Page<FollowInfoResponseDto> findByReceiverUuid(String uuid, Pageable pageable) {
		List<FollowInfo> followerList = jpaQueryFactory.selectFrom(followInfo)
			.where(followInfo.receiverUuid.eq(uuid))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize() + 1)
			.fetch();

		// 총 데이터 개수 계산
		long total = jpaQueryFactory.select(followInfo.count())
			.from(followInfo)
			.where(followInfo.receiverUuid.eq(uuid))
			.fetchOne();

		// DTO로 변환
		List<FollowInfoResponseDto> followerInfoList = followerList.stream().map(followInfo -> {
			FollowInfoResponseDto followerInfo = FollowInfoResponseDto.builder()
				.uuid(followInfo.getSenderUuid())
				.tag(followInfo.getSenderTag())
				.image(followInfo.getSenderImage())
				.nickname(followInfo.getSenderNickname())
				.build();
			return followerInfo;
		}).toList();

		// Page 반환
		return new PageImpl<>(followerInfoList, pageable, total);
	}

	@Override
	public Page<FollowInfoResponseDto> findBySenderUuid(String uuid, Pageable pageable) {
		List<FollowInfo> followingList = jpaQueryFactory.selectFrom(followInfo)
			.where(followInfo.senderUuid.eq(uuid))
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize() + 1)
			.fetch();

		// 총 데이터 개수 계산
		long total = jpaQueryFactory.select(followInfo.count())
			.from(followInfo)
			.where(followInfo.senderUuid.eq(uuid))
			.fetchOne();

		// DTO로 변환
		List<FollowInfoResponseDto> followingInfoList = followingList.stream().map(follow -> {
			FollowInfoResponseDto followingInfo = FollowInfoResponseDto.builder()
				.uuid(follow.getReceiverUuid())
				.tag(follow.getReceiverTag())
				.image(follow.getReceiverImage())
				.nickname(follow.getReceiverNickname())
				.build();
			return followingInfo;
		}).toList();

		// Page 반환
		return new PageImpl<>(followingInfoList, pageable, total);

	}

}
