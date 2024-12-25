package lookids.mono.favorite.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FavoriteFeedDto {
	private String uuid;
	private List<String> targetCodeList;
}
