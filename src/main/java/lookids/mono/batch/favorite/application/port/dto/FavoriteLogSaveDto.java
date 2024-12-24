package lookids.mono.batch.favorite.application.port.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.batch.favorite.domain.FavoriteType;

@Getter
@NoArgsConstructor
public class FavoriteLogSaveDto {
	private Long id;
	private String uuid;
	private String targetCode;
	private String logType;
	private FavoriteType favoriteType;
	private LocalDateTime createdAt;
	private Boolean processed;

	@Builder
	public FavoriteLogSaveDto(Long id, String uuid, String targetCode, String logType, FavoriteType favoriteType,
		LocalDateTime createdAt, Boolean processed) {
		this.id = id;
		this.uuid = uuid;
		this.targetCode = targetCode;
		this.logType = logType;
		this.favoriteType = favoriteType;
		this.createdAt = createdAt;
		this.processed = processed;
	}
}
