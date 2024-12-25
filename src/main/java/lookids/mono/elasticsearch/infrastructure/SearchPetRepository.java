package lookids.mono.elasticsearch.infrastructure;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import lookids.mono.elasticsearch.domain.SearchPet;

@Repository
public interface SearchPetRepository extends ElasticsearchRepository<SearchPet, String> {

	void save(List<SearchPet> searchPet);

	SearchPet findByPetCode(String petCode);

	@Query("{\"wildcard\": {\"petName\": \"*?0*\"}}")
	Page<SearchPet> findByPetName(String petName, Pageable pageable);

	void deleteByPetCode(List<String> petCode);

}
