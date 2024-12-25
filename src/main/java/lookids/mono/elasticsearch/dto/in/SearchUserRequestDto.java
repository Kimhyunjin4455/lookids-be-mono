package lookids.mono.elasticsearch.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SearchUserRequestDto {

	private String userNickname;
	private String tag;

	@Builder
	public SearchUserRequestDto(String userNickname, String tag) {
		this.userNickname = userNickname;
		this.tag = tag;
	}

}
