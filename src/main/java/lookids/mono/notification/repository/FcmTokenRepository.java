package lookids.alarm.notification.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import lookids.alarm.notification.domain.FcmToken;

@Repository
public interface FcmTokenRepository extends MongoRepository<FcmToken, String> {
	Optional<FcmToken> findByUuid(String uuid);
}
