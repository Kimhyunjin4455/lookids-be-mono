package lookids.chatting.chatting.infrastructure;

import lookids.chatting.chatting.domain.ChatMessage;
import lookids.chatting.common.utils.CursorPage;

public interface ChatMessageRepositoryCustom {
	CursorPage<ChatMessage> getChatMessage(
		String roomId,
		String lastId,
		Integer pageSize,
		Integer page
	);
}