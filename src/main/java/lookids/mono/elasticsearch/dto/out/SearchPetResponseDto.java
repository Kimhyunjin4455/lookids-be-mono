package lookids.elasticsearch.dto.out;

import lombok.Builder;
import lombok.Getter;
import lookids.elasticsearch.domain.SearchPet;
import lookids.elasticsearch.vo.SearchPetResponseVo;

@Getter
public class SearchPetResponseDto {

	private String petName;
	private String petType;
	private String petImage;
	private String petCode;
	private String userNickname;
	private String userTag;

	@Builder
	public SearchPetResponseDto(String petName, String petType, String petImage, String petCode, String userNickname, String userTag) {
		this.petName = petName;
		this.petType = petType;
		this.petImage = petImage;
		this.petCode = petCode;
		this.userNickname = userNickname;
		this.userTag = userTag;
	}

	public static SearchPetResponseDto toDto(SearchPet searchPet) {
		return SearchPetResponseDto.builder()
			.petName(searchPet.getPetName())
			.petType(searchPet.getPetType())
			.petImage(searchPet.getPetImage())
			.petCode(searchPet.getPetCode())
			.userNickname(searchPet.getUserNickname())
			.userTag(searchPet.getUserTag())
			.build();
	}

	public SearchPetResponseVo toVo() {
		return SearchPetResponseVo.builder()
			.petName(this.petName)
			.petType(this.petType)
			.petImage(this.petImage)
			.petCode(this.petCode)
			.userNickname(this.userNickname)
			.userTag(this.userTag)
			.build();
	}

}
