package lookids.mono.report.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RequestType {

	INQUIRY("문의"), REPORT("신고");

	private final String requestType;

	@JsonValue
	public String getRequestType() {
		return requestType;
	}

	@JsonCreator
	public static RequestType fromValue(String value) {
		for (RequestType requestType : RequestType.values()) {
			if (requestType.requestType.equals(value)) {
				return requestType;
			}
		}
		throw new IllegalArgumentException("UnKnown value: " + value);
	}
}
