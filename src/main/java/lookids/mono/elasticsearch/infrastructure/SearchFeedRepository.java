package lookids.elasticsearch.infrastructure;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import lookids.elasticsearch.domain.SearchFeed;

@Repository
public interface SearchFeedRepository extends ElasticsearchRepository<SearchFeed, String> {

	void save(List<SearchFeed> searchFeeds);
	Page<SearchFeed> findByTagListContaining(String tag, Pageable pageable);
	Page<SearchFeed> findByPetCode(String petCode, Pageable pageable);
	void deleteByFeedCode(List<String> feedCode);

}
