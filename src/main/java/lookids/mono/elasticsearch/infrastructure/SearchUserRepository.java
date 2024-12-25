package lookids.mono.elasticsearch.infrastructure;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import lookids.mono.elasticsearch.domain.SearchUser;

@Repository
public interface SearchUserRepository extends ElasticsearchRepository<SearchUser, String> {

	void save(List<SearchUser> searchUsers);

	SearchUser findByUuid(String uuid);

	@Query("{\"wildcard\": {\"nickname\": \"*?0*\"}}")
	Page<SearchUser> findByNickname(String nickname, Pageable pageable);

	Page<SearchUser> findByNicknameAndTag(String nickname, String tag, Pageable pageable);

}
