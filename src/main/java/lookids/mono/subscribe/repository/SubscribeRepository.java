package lookids.subscribe.subscribe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import lookids.subscribe.subscribe.domain.Subscribe;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
	List<Subscribe> findByAuthorUuid(String authorUuid);
	Long deleteByAuthorUuidAndSubscriberUuid(String authorUuid, String subscriberUuid);
	Subscribe findByAuthorUuidAndSubscriberUuid(String authorUuid, String subscriberUuid);
	boolean existsByAuthorUuidAndSubscriberUuid(String authorUuid, String subscriberUuid);
}

