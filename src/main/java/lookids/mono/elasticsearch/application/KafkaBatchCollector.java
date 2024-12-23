package lookids.elasticsearch.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import lookids.elasticsearch.domain.SearchFeed;
import lookids.elasticsearch.domain.SearchPet;
import lookids.elasticsearch.domain.SearchUser;

@Slf4j
@Component
public class KafkaBatchCollector {

	private final Queue<SearchUser> UserCreateBatchQueue = new ConcurrentLinkedQueue<>();
	private final Queue<SearchUser> UserUpdateBatchQueue = new ConcurrentLinkedQueue<>();
	private final Queue<SearchUser> UserDeleteBatchQueue = new ConcurrentLinkedQueue<>();
	private final Queue<SearchFeed> FeedCreateBatchQueue = new ConcurrentLinkedQueue<>();
	private final Queue<String> FeedDeleteBatchQueue = new ConcurrentLinkedQueue<>();
	private final Queue<SearchPet> PetCreateBatchQueue = new ConcurrentLinkedQueue<>();
	private final Queue<SearchPet> PetUpdateBatchQueue = new ConcurrentLinkedQueue<>();
	private final Queue<String> PetDeleteBatchQueue = new ConcurrentLinkedQueue<>();

	public void addUserCreateBatch(SearchUser searchUser) {
		UserCreateBatchQueue.add(searchUser);
	}

	public void addUserUpdateBatch(SearchUser searchUser) {
		UserUpdateBatchQueue.add(searchUser);
	}

	public void addUserDeleteBatch(SearchUser searchUser) {
		UserDeleteBatchQueue.add(searchUser);
	}

	public void addFeedCreateBatch(SearchFeed searchFeed) {
		FeedCreateBatchQueue.add(searchFeed);
	}

	public void addFeedDeleteBatch(String feedCode) {
		FeedDeleteBatchQueue.add(feedCode);
	}

	public void addPetCreateBatch(SearchPet searchPet) {
		PetCreateBatchQueue.add(searchPet);
	}

	public void addPetUpdateBatch(SearchPet searchPet) {
		PetUpdateBatchQueue.add(searchPet);
	}

	public void addPetDeleteBatch(String petCode) {
		PetDeleteBatchQueue.add(petCode);
	}

	public List<SearchUser> getUserCreateBatch() {
		List<SearchUser> batch = new ArrayList<>();
		SearchUser searchUser;
		while ((searchUser = UserCreateBatchQueue.poll()) != null) {
			batch.add(searchUser);
		}
		return batch;
	}

	public List<SearchUser> getUserUpdateBatch() {
		List<SearchUser> batch = new ArrayList<>();
		SearchUser searchUser;
		while ((searchUser = UserUpdateBatchQueue.poll()) != null) {
			batch.add(searchUser);
		}
		return batch;
	}

	public List<SearchUser> getUserDeleteBatch() {
		List<SearchUser> batch = new ArrayList<>();
		SearchUser searchUser;
		while ((searchUser = UserDeleteBatchQueue.poll()) != null) {
			batch.add(searchUser);
		}
		return batch;
	}

	public List<SearchFeed> getFeedCreateBatch() {
		List<SearchFeed> batch = new ArrayList<>();
		SearchFeed searchFeed;
		while ((searchFeed = FeedCreateBatchQueue.poll()) != null) {
			batch.add(searchFeed);
		}
		return batch;
	}

	public List<String> getFeedDeleteBatch() {
		List<String> batch = new ArrayList<>();
		String deleteFeed;
		while ((deleteFeed = FeedDeleteBatchQueue.poll()) != null) {
			batch.add(deleteFeed);
		}
		return batch;
	}

	public List<SearchPet> getPetCreateBatch() {
		List<SearchPet> batch = new ArrayList<>();
		SearchPet searchPet;
		while ((searchPet = PetCreateBatchQueue.poll()) != null) {
			batch.add(searchPet);
		}
		return batch;
	}

	public List<SearchPet> getPetUpdateBatch() {
		List<SearchPet> batch = new ArrayList<>();
		SearchPet searchPet;
		while ((searchPet = PetUpdateBatchQueue.poll()) != null) {
			batch.add(searchPet);
		}
		return batch;
	}

	public List<String> getPetDeleteBatch() {
		List<String> batch = new ArrayList<>();
		String petCode;
		while ((petCode = PetDeleteBatchQueue.poll()) != null) {
			batch.add(petCode);
		}
		return batch;
	}

}
