package lookids.elasticsearch.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.elasticsearch.domain.SearchPet;

@Getter
@NoArgsConstructor
public class KafkaPetUpdateRequestDto {

	private String id;
	private String userNickname;
	private String userTag;
	private String petCode;
	private String image;
	private String petName;
	private String petType;

	@Builder
	public KafkaPetUpdateRequestDto(
		String id,
		String userNickname,
		String userTag,
		String petCode,
		String image,
		String petName,
		String petType
	) {
		this.id = id;
		this.userNickname = userNickname;
		this.userTag = userTag;
		this.petCode = petCode;
		this.image = image;
		this.petName = petName;
		this.petType = petType;
	}

	public static KafkaPetUpdateRequestDto toUpdate(SearchPet searchPet, KafkaPetUpdateRequestDto kafkaPetUpdateRequestDto) {
		return KafkaPetUpdateRequestDto.builder()
			.id(searchPet.getId())
			.userNickname(searchPet.getUserNickname())
			.userTag(searchPet.getUserTag())
			.petCode(searchPet.getPetCode())
			.image(kafkaPetUpdateRequestDto.getImage())
			.petName(kafkaPetUpdateRequestDto.getPetName())
			.petType(kafkaPetUpdateRequestDto.getPetType())
			.build();
	}

	public SearchPet toEntity() {
		return SearchPet.builder()
			.id(this.id)
			.userNickname(this.userNickname)
			.userTag(this.userTag)
			.petCode(this.petCode)
			.petImage(this.image)
			.petName(this.petName)
			.petType(this.petType)
			.build();
	}

}
