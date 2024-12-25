package lookids.mono.chatting.infrastructure;

import lookids.mono.chatting.domain.ChatRoom;
import lookids.mono.common.utils.CursorPage;

public interface ChatRoomRepositoryCustom {
	CursorPage<ChatRoom> getChatRoom(String roomId, String lastId, Integer pageSize, Integer page);
}
