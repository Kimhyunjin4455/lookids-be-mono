package lookids.manager.information.dto.out;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InformationUpdateResponseDto {

	private String title;
	private String content;
	private List<InformationMediaDto> mediaUrls;
	private LocalDateTime updatedAt;

	public InformationUpdateResponseDto toDto() {
		return InformationUpdateResponseDto.builder()
			.title(title)
			.content(content)
			.mediaUrls(mediaUrls)
			.build();
	}
}
