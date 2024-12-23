package lookids.manager.information.vo.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.manager.information.dto.out.InformationMediaDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InformationResponseVo {

	private String title;
	private String content;
	private List<InformationMediaDto> mediaUrls;
	private LocalDateTime createdAt;

}
