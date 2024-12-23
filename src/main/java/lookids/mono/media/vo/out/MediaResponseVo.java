package lookids.mono.media.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class MediaResponseVo {

	private String userUuid;
	private String mediaCode;
	private String mediaType;
	private String mediaUrl;
	private Double latitude;
	private Double longitude;
}
