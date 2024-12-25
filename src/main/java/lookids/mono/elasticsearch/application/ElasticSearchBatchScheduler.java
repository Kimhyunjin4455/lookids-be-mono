package lookids.mono.elasticsearch.application;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.mono.elasticsearch.domain.SearchFeed;
import lookids.mono.elasticsearch.domain.SearchPet;
import lookids.mono.elasticsearch.domain.SearchUser;
import lookids.mono.elasticsearch.infrastructure.SearchFeedRepository;
import lookids.mono.elasticsearch.infrastructure.SearchPetRepository;
import lookids.mono.elasticsearch.infrastructure.SearchUserRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class ElasticSearchBatchScheduler {

	private final KafkaBatchCollector kafkaBatchCollector;
	private final SearchUserRepository searchUserRepository;
	private final SearchFeedRepository searchFeedRepository;
	private final SearchPetRepository searchPetRepository;

	private final ElasticsearchRestTemplate elasticsearchRestTemplate;

	@Scheduled(fixedDelay = 60000) // 1분마다 실행
	public void UserSaveBatch() {
		List<SearchUser> batch = kafkaBatchCollector.getUserCreateBatch();
		searchUserRepository.save(batch);
	}

	@Scheduled(fixedDelay = 60000)
	public void UserUpdateBatch() {
		List<SearchUser> batch = kafkaBatchCollector.getUserUpdateBatch();
		searchUserRepository.save(batch);
	}

	@Scheduled(fixedDelay = 60000)
	public void UserDeleteBatch() {
		List<SearchUser> batch = kafkaBatchCollector.getUserDeleteBatch();
		searchUserRepository.save(batch);
	}

	@Scheduled(fixedDelay = 60000)
	public void FeedSaveBatch() {
		List<SearchFeed> batch = kafkaBatchCollector.getFeedCreateBatch();
		searchFeedRepository.save(batch);
	}

	@Scheduled(fixedDelay = 60000)
	public void FeedDeleteBatch() {
		List<String> batch = kafkaBatchCollector.getFeedDeleteBatch();

		NativeSearchQuery query = new NativeSearchQuery(QueryBuilders.termsQuery("feedCode", batch));
		elasticsearchRestTemplate.delete(query, SearchFeed.class);

		searchFeedRepository.deleteByFeedCode(batch);
	}

	@Scheduled(fixedDelay = 60000)
	public void PetCreateBatch() {
		List<SearchPet> batch = kafkaBatchCollector.getPetCreateBatch();
		searchPetRepository.save(batch);
	}

	@Scheduled(fixedDelay = 60000)
	public void PetUpdateBatch() {
		List<SearchPet> batch = kafkaBatchCollector.getPetUpdateBatch();
		searchPetRepository.save(batch);
	}

	@Scheduled(fixedDelay = 60000)
	public void PetDeleteBatch() {
		List<String> batch = kafkaBatchCollector.getPetDeleteBatch();

		NativeSearchQuery query = new NativeSearchQuery(QueryBuilders.termsQuery("petCode", batch));
		elasticsearchRestTemplate.delete(query, SearchPet.class);

		searchPetRepository.deleteByPetCode(batch);
	}

}
