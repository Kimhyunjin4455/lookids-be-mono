package lookids.mono.chatting.infrastructure;

import lookids.mono.chatting.domain.ChatMessage;
import lookids.mono.common.utils.CursorPage;

public interface ChatMessageRepositoryCustom {
	CursorPage<ChatMessage> getChatMessage(String roomId, String lastId, Integer pageSize, Integer page);
}