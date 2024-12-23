package lookids.manager.information.vo.in;

import java.util.List;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class InformationUpdateRequestVo {

	private String title;
	private String content;
	private List<String> mediaUrls;

}
