package Lookids.block.infrastructure;

import static Lookids.block.domain.QBlock.*;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import Lookids.block.domain.Block;
import Lookids.block.dto.out.BlockResponseDto;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BlockRepositoryCustomImpl implements BlockRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Page<BlockResponseDto> findByUuidAndStateTrue(String userUuid, Pageable pageable) {

		List<Block> blockList = jpaQueryFactory
			.selectFrom(block)
			.where(block.uuid.eq(userUuid), block.state.isTrue())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize() + 1)
			.fetch();

		// 총 데이터 개수 계산
		long total = jpaQueryFactory
			.select(block.count())
			.from(block)
			.where(block.uuid.eq(userUuid))
			.fetchOne();

		List<BlockResponseDto> blockResponseDtoList = blockList.stream()
			.map(block -> {
				BlockResponseDto blockResponseDto = BlockResponseDto.toDto(block);
				return blockResponseDto;
			})
			.toList();

		return new PageImpl<>(blockResponseDtoList, pageable, total);

	}
}
