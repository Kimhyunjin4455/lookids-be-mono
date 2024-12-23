package lookids.batch.favorite.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FavoriteType {

	FEED("피드"), COMMENT("댓글");

	private final String favoriteType;

	@JsonValue
	public String getFavoriteType() {
		return favoriteType;
	}

	@JsonCreator
	public static FavoriteType fromValue(String value) {
		for (FavoriteType favoriteType : FavoriteType.values()) {
			if (favoriteType.favoriteType.equals(value)) {
				return favoriteType;
			}
		}
		throw new IllegalArgumentException("Unknown value: " + value);
	}
}

