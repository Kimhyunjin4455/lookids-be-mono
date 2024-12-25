package lookids.mono.chatting.dto.out;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.bson.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.mono.chatting.domain.ChatRoom;
import lookids.mono.chatting.domain.Participant;
import lookids.mono.chatting.vo.out.ChatRoomResponseVo;
import lookids.mono.common.entity.BaseResponseStatus;
import lookids.mono.common.exception.BaseException;

@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class ChatRoomResponseDto {
	private String roomId;
	private String roomName;
	private Long unreadCount;
	private List<String> participants;
	private String lastChatMessageAt;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public static ChatRoomResponseDto toDto(ChatRoom chatRoom, String userId) {
		// 요청한 userId에 해당하는 Participant 찾기
		Participant participant = chatRoom.getParticipants()
			.stream()
			.filter(p -> p.getUserId().equals(userId))
			.findFirst()
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_IN_USER));

		// participants 리스트에서 userId만 추출
		List<String> participantUserIds = chatRoom.getParticipants().stream().map(Participant::getUserId).toList();

		return ChatRoomResponseDto.builder()
			.roomId(chatRoom.getId())
			.roomName(chatRoom.getRoomName())
			.unreadCount(participant.getUnreadCount()) // 해당 userId의 unreadCount
			.participants(participantUserIds) // userId 리스트 추가
			.lastChatMessageAt(chatRoom.getLastChatMessageAt())

			.createdAt(chatRoom.getCreatedAt()
				.atZone(ZoneId.systemDefault())
				.withZoneSameInstant(ZoneId.of("Asia/Seoul"))
				.toLocalDateTime())
			.updatedAt(chatRoom.getUpdatedAt()
				.atZone(ZoneId.systemDefault())
				.withZoneSameInstant(ZoneId.of("Asia/Seoul"))
				.toLocalDateTime())

			.build();
	}

	public static ChatRoomResponseDto fromDocument(Document document, String userId) {
		// participants 필드에서 userId 리스트를 추출
		List<Document> participantDocs = document.getList("participants", Document.class);
		List<String> participantUserIds = participantDocs.stream().map(p -> p.getString("userId")) // userId만 추출
			.toList();

		// 요청한 userId에 해당하는 Participant 데이터 찾기
		Document participantDoc = participantDocs.stream()
			.filter(p -> userId.equals(p.getString("userId")))
			.findFirst()
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_IN_USER));

		Long unreadCount = participantDoc.getLong("unreadCount");

		return ChatRoomResponseDto.builder()
			.roomId(document.getObjectId("_id").toString())
			.roomName(document.getString("roomName"))
			.unreadCount(unreadCount) // 해당 userId의 unreadCount
			.participants(participantUserIds) // userId 리스트 추가
			.lastChatMessageAt(document.getString("lastChatMessageAt"))

			.createdAt(LocalDateTime.ofInstant(document.getDate("createdAt").toInstant(), ZoneId.of("Asia/Seoul")))
			.updatedAt(LocalDateTime.ofInstant(document.getDate("updatedAt").toInstant(), ZoneId.of("Asia/Seoul")))

			.build();
	}

	public ChatRoomResponseVo toVo() {
		return ChatRoomResponseVo.builder()
			.roomId(roomId)
			.roomName(roomName)
			.unreadCount(unreadCount)
			.participants(participants) // userId 리스트
			.lastChatMessageAt(lastChatMessageAt)
			.createdAt(createdAt)
			.updatedAt(updatedAt)
			.build();
	}
}
