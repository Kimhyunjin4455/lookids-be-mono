package lookids.mono.chatting.infrastructure;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import lookids.mono.chatting.domain.ChatRoom;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {
	@Query("{ 'participants.userId': ?0 }")
	Page<ChatRoom> findByParticipantUserId(String userId, Pageable pageable);

	@Query("{ 'participants': { $size: 2, $all: [ { $elemMatch: { 'userId': ?0 } }, { $elemMatch: { 'userId': ?1 } } ] } }")
	Optional<ChatRoom> findOneToOneChatRoom(String userId1, String userId2);

}
