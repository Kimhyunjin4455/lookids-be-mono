package lookids.mono.media.vo.out;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class MediaListResponseVo {

	private List<String> mediaCode;
}
