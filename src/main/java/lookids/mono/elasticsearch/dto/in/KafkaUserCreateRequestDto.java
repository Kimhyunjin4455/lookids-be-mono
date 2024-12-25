package lookids.mono.elasticsearch.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class KafkaUserCreateRequestDto {

	private String uuid;
	private String nickname;
	private String tag;
	private String image;

	@Builder
	public KafkaUserCreateRequestDto(String uuid, String nickname, String tag, String image) {
		this.uuid = uuid;
		this.nickname = nickname;
		this.tag = tag;
		this.image = image;
	}

}
