package lookids.mono.user.petprofile.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.user.petprofile.domain.PetProfile;
import lookids.mono.user.petprofile.vo.in.PetProfileUpdateVo;

@Getter
@NoArgsConstructor
public class PetProfileUpdateDto {

	private String petCode;
	private String name;
	private String comment;
	private String gender;
	private Integer age;
	private String type;
	private Float weight;
	private String image;

	@Builder
	public PetProfileUpdateDto(String petCode, String name, String comment, String gender, Integer age, String type,
		Float weight, String image) {
		this.petCode = petCode;
		this.name = name;
		this.comment = comment;
		this.gender = gender;
		this.age = age;
		this.type = type;
		this.weight = weight;
		this.image = image;
	}

	public static PetProfileUpdateDto toDto(PetProfileUpdateVo petProfileUpdateVo) {
		return PetProfileUpdateDto.builder()
			.petCode(petProfileUpdateVo.getPetCode())
			.name(petProfileUpdateVo.getName())
			.comment(petProfileUpdateVo.getComment())
			.gender(petProfileUpdateVo.getGender())
			.age(petProfileUpdateVo.getAge())
			.type(petProfileUpdateVo.getType())
			.weight(petProfileUpdateVo.getWeight())
			.image(petProfileUpdateVo.getImage())
			.build();
	}

	public PetProfile toEntity(PetProfile petProfile) {
		return PetProfile.builder()
			.id(petProfile.getId())
			.userUuid(petProfile.getUserUuid())
			.petCode(petProfile.getPetCode())
			.name(name)
			.comment(comment)
			.gender(gender)
			.age(age)
			.type(type)
			.weight(weight)
			.image(image)
			.build();
	}
}
