package lookids.manager.information.dto.in;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.manager.information.vo.in.InformationRequestVo;
import lookids.manager.information.vo.in.InformationUpdateRequestVo;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InformationUpdateRequestDto {

	private String feedCode;
	private String title;
	private String content;
	private List<String> mediaUrls;

	public static InformationUpdateRequestDto toDto(String feedCode, InformationUpdateRequestVo informationUpdateRequestVo) {
		return InformationUpdateRequestDto.builder()
			.feedCode(feedCode)
			.title(informationUpdateRequestVo.getTitle())
			.content(informationUpdateRequestVo.getContent())
			.mediaUrls(informationUpdateRequestVo.getMediaUrls())
			.build();
	}
}
