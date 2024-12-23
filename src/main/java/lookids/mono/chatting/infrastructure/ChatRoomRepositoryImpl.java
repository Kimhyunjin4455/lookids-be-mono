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
import lookids.chatting.chatting.domain.ChatRoom;
import lookids.chatting.common.utils.CursorPage;

@Slf4j
@RequiredArgsConstructor
@Repository
public class ChatRoomRepositoryImpl implements ChatRoomRepositoryCustom {
	private final MongoTemplate mongoTemplate;

	@Override
	public CursorPage<ChatRoom> getChatRoom(String userId, String lastId, Integer pageSize, Integer page) {
		Query query = new Query();
		query.addCriteria(Criteria.where("participants").elemMatch(Criteria.where("userId").is(userId)));

		if (lastId != null) {
			query.addCriteria(Criteria.where("_id").lt(new ObjectId(lastId)));
		}

		// Pagination & 정렬
		query.limit(pageSize + 1);
		query.with(Sort.by(Sort.Direction.DESC, "updatedAt"));

		List<ChatRoom> chatRoom = mongoTemplate.find(query, ChatRoom.class);

		// 다음 페이지 여부 확인
		boolean hasNext = chatRoom.size() > pageSize;
		String nextCursor = hasNext ? chatRoom.get(pageSize).getId() : null;

		if (hasNext) {
			chatRoom = chatRoom.subList(0, pageSize);
		}

		return new CursorPage<>(chatRoom, nextCursor, hasNext, pageSize, page);
	}
}
