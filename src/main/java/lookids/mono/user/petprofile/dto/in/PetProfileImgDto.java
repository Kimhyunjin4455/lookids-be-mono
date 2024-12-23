package lookids.mono.user.petprofile.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.user.petprofile.domain.PetProfile;
import lookids.mono.user.petprofile.vo.in.PetProfileImgVo;

@Getter
@NoArgsConstructor
public class PetProfileImgDto {

	private String userUuid;
	private String petCode;
	private String image;

	@Builder
	public PetProfileImgDto(String userUuid, String petCode, String image) {
		this.userUuid = userUuid;
		this.petCode = petCode;
		this.image = image;
	}

	public static PetProfileImgDto toDto(PetProfileImgVo petProfileImgVo, String userUuid) {
		return PetProfileImgDto.builder()
			.userUuid(userUuid)
			.petCode(petProfileImgVo.getPetCode())
			.image(petProfileImgVo.getImage())
			.build();
	}

	public PetProfile toEntity(PetProfile petProfile) {
		return PetProfile.builder()
			.id(petProfile.getId())
			.userUuid(petProfile.getUserUuid())
			.petCode(petProfile.getPetCode())
			.name(petProfile.getName())
			.age(petProfile.getAge())
			.type(petProfile.getType())
			.weight(petProfile.getWeight())
			.image(image)
			.build();
	}
}
