package lookids.map.map.infrastructure;

import java.util.List;

import lookids.map.map.domain.Map;

public interface MapRepositoryCustom {
	List<Map> findPinsByArea(String uuid, Double ha, Double qa, Double oa, Double pa);
	List<Map> findBasicPinsByArea(String category, Double ha, Double qa, Double oa, Double pa);
}
