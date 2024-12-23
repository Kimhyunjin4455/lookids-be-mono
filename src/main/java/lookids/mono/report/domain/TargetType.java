package lookids.report.report.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TargetType {
	COMMENT("댓글"),
	FEED("피드"),
	USER("유저"),
	REPLY("대댓글"),
	DM("DM");

	private final String targetType;

	@JsonValue
	public String getTargetType() {
		return targetType;
	}

	@JsonCreator
	public static TargetType fromValue(String value) {
		for (TargetType targetType : TargetType.values()) {
			if (targetType.targetType.equals(value)) {
				return targetType;
			}
		}
		throw new IllegalArgumentException("UnKnown value: " + value);

	}
}
