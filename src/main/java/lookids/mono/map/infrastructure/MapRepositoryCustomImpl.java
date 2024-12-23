package lookids.map.map.infrastructure;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import lookids.map.map.domain.Map;
import lookids.map.map.domain.QMap;

@Repository
@RequiredArgsConstructor
public class MapRepositoryCustomImpl implements MapRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<Map> findPinsByArea(String uuid, Double ha, Double oa, Double pa, Double qa) {
		QMap map = QMap.map;

		return queryFactory
			.selectFrom(map)
			.where(
				map.uuid.eq(uuid),
				map.latitude.between(ha, pa),
				map.longitude.between(oa, qa)
			).fetch();

	}

	@Override
	public List<Map> findBasicPinsByArea(String category, Double ha, Double oa, Double pa, Double qa) {
		QMap map = QMap.map;

		return queryFactory
			.selectFrom(map)
			.where(
				map.feedCode.isNull(),
				map.category.eq(category),
				map.latitude.between(ha, pa),
				map.longitude.between(oa, qa)
			).fetch();
	}
}
