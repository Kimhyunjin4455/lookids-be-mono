package lookids.subscribe.subscribe.dto.in;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FeedKafkaRequestDto {
	private String uuid; 			   // Feed에서 send로 보낸 값과 통일, 피드 작성자 uuid
	private List<String> mediaUrlList; // 첫 이미지만 알림 서비스로 전송
	private String feedCode;
	private String content;		   // 피드 내용 적절히 잘라서 알림 서비스로 전송

	@Builder
	public FeedKafkaRequestDto(
		String uuid,
		List<String> mediaUrlList,
		String feedCode,
		String content
	) {
		this.uuid = uuid;
		this.mediaUrlList = mediaUrlList;
		this.feedCode = feedCode;
		this.content = content;
	}
}
