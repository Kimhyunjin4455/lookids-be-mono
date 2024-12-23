package lookids.manager.information.vo.out;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.manager.information.dto.out.InformationMediaDto;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InformationUpdateResponseVo {

	private String title;
	private String content;
	private List<InformationMediaDto> mediaUrls;
	private LocalDateTime updatedAt;
}
