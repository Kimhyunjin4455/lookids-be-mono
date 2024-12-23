package Lookids.Feed.feed.infrastructure;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import Lookids.Feed.feed.domain.Feed;

public interface FeedRepository extends MongoRepository<Feed, ObjectId> {
	Optional<Feed> findByFeedCodeAndStateTrue(String feedCode);
}
