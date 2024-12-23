package Lookids.follow.infrastructure;

import static Lookids.follow.domain.QFollow.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import Lookids.follow.domain.Follow;
import Lookids.follow.dto.out.FollowResponseDto;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FollowRepositoryCustomImpl implements FollowRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Page<FollowResponseDto> findByFollowerUuid(String uuid, Pageable pageable) {
		List<Follow> followerList = jpaQueryFactory
			.selectFrom(follow)
			.where(follow.followerUuid.eq(uuid))
			.orderBy(follow.createdAt.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize() + 1)
			.fetch();

		// 총 데이터 개수 계산
		long total = jpaQueryFactory
			.select(follow.count())
			.from(follow)
			.where(follow.followerUuid.eq(uuid))
			.fetchOne();

		// DTO로 변환
		List<FollowResponseDto> followResponseDtoList = followerList.stream()
			.map(follow -> {
				FollowResponseDto followResponseDto = new FollowResponseDto();
				followResponseDto.setFollowerUuid(follow.getFollowerUuid());
				followResponseDto.setFollowingUuid(follow.getFollowingUuid());
				return followResponseDto;
			})
			.toList();

		// Page 반환
		return new PageImpl<>(followResponseDtoList, pageable, total);
	}

	@Override
	public Page<FollowResponseDto> findByFollowingUuid(String uuid, Pageable pageable) {
		List<Follow> followingList = jpaQueryFactory
			.selectFrom(follow)
			.where(follow.followingUuid.eq(uuid))
			.orderBy(follow.createdAt.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize() + 1)
			.fetch();

		// 총 데이터 개수 계산
		long total = jpaQueryFactory
			.select(follow.count())
			.from(follow)
			.where(follow.followingUuid.eq(uuid))
			.fetchOne();

		// DTO로 변환
		List<FollowResponseDto> followResponseDtoList = followingList.stream()
			.map(follow -> {
				FollowResponseDto followResponseDto = new FollowResponseDto();
				followResponseDto.setFollowerUuid(follow.getFollowerUuid());
				followResponseDto.setFollowingUuid(follow.getFollowingUuid());
				return followResponseDto;
			})
			.toList();

		// Page 반환
		return new PageImpl<>(followResponseDtoList, pageable, total);

	}
}
