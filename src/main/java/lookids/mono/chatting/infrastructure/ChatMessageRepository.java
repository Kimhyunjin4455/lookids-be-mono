package lookids.mono.chatting.infrastructure;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import lookids.mono.chatting.domain.ChatMessage;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
	@Query("{ 'roomId' : ?0 }")
	Page<ChatMessage> findByRoomId(String roomId, Pageable pageable);

	Optional<ChatMessage> findTopByRoomIdOrderByCreatedAtDesc(String roomId);

	void deleteAllByRoomId(String roomId);
}
