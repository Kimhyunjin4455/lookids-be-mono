package lookids.mono.batch.favorite.application.port.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.batch.favorite.domain.FavoriteType;

@Getter
@NoArgsConstructor
public class FavoriteCountSaveDto {
	private Long id;
	private String targetCode;
	private Integer count;
	private FavoriteType type;
	private LocalDateTime updateAt;

	@Builder
	public FavoriteCountSaveDto(Long id, String targetCode, Integer count, FavoriteType type, LocalDateTime updateAt) {
		this.id = id;
		this.targetCode = targetCode;
		this.count = count;
		this.type = type;
		this.updateAt = updateAt;
	}
}
