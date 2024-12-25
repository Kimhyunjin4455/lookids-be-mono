package lookids.mono.elasticsearch.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KafkaPetCreateRequestDto {

	private String petName;
	private String petType;
	private String petImage;
	private String petCode;
	private String userNickname;
	private String userTag;

	@Builder
	public KafkaPetCreateRequestDto(String petName, String petType, String petImage, String petCode,
		String userNickname, String userTag) {
		this.petName = petName;
		this.petType = petType;
		this.petImage = petImage;
		this.petCode = petCode;
		this.userNickname = userNickname;
		this.userTag = userTag;
	}

}
