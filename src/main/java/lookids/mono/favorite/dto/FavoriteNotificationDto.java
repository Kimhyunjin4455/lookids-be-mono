package lookids.mono.favorite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.mono.favorite.domain.FavoriteType;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FavoriteNotificationDto {
	private String senderUuid;
	private String receiverUuid;
	private String targetCode;
	private String content;

	public static FavoriteNotificationDto toDto(FavoriteRequestDto favoriteRequestDto) {

		String content =
			favoriteRequestDto.getFavoriteType() == FavoriteType.FEED ? "님이 피드에 좋아요를 눌렀습니다" : "님이 댓글에 좋아요를 눌렀습니다";

		return FavoriteNotificationDto.builder()
			.senderUuid(favoriteRequestDto.getUuid())
			.receiverUuid(favoriteRequestDto.getAuthorUuid())
			.targetCode(favoriteRequestDto.getTargetCode())
			.content(content)
			.build();
	}
}
