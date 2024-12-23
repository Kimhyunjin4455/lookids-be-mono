package lookids.chatting.chatting.infrastructure;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.chatting.chatting.domain.ChatMessage;
import lookids.chatting.common.utils.CursorPage;

@Slf4j
@RequiredArgsConstructor
@Repository
public class ChatMessageRepositoryImpl implements ChatMessageRepositoryCustom {

	private final MongoTemplate mongoTemplate;

	@Override
	public CursorPage<ChatMessage> getChatMessage(String roomId, String lastId, Integer pageSize, Integer page) {
		Query query = new Query();
		query.addCriteria(Criteria.where("roomId").is(roomId));

		if (lastId != null) {
			query.addCriteria(Criteria.where("_id").lt(new ObjectId(lastId)));
		}

		query.limit(pageSize + 1);
		query.with(Sort.by(Sort.Direction.DESC, "createdAt"));

		List<ChatMessage> chatMessages = mongoTemplate.find(query, ChatMessage.class);

		boolean hasNext = chatMessages.size() > pageSize;
		String nextCursor = hasNext ? chatMessages.get(pageSize).getId() : null;

		if (hasNext) {
			chatMessages = chatMessages.subList(0, pageSize);
		}

		return new CursorPage<>(chatMessages, nextCursor, hasNext, pageSize, page);
	}
}