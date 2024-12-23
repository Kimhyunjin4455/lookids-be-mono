package lookids.mono.media.vo.in;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MediaRequestVo {
	private String mediaType;
	private String mediaUrl;
	private Double latitude;
	private Double longitude;
}
