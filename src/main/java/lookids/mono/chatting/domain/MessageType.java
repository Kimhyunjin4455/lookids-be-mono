package lookids.mono.chatting.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageType {
	IMAGE("이미지"), TEXT("텍스트");

	private final String messageType;

	@JsonValue
	public String getMessageType() {
		return messageType;
	}

	@JsonCreator
	public static MessageType fromValue(String value) {
		for (MessageType messageType : MessageType.values()) {
			if (messageType.messageType.equals(value)) {
				return messageType;
			}
		}
		throw new IllegalArgumentException("Unknown value: " + value);
	}
}
