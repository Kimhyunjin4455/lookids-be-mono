package lookids.mono.batch.favorite.application.port.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FavoriteCountResponseDto {
	private Integer count;

	@Builder
	public FavoriteCountResponseDto(Integer count) {
		this.count = count;
	}
}
