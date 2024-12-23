package lookids.chatting.chatting.infrastructure;

import lookids.chatting.chatting.domain.ChatRoom;
import lookids.chatting.common.utils.CursorPage;

public interface ChatRoomRepositoryCustom {
	CursorPage<ChatRoom> getChatRoom(
		String roomId,
		String lastId,
		Integer pageSize,
		Integer page
	);
}
