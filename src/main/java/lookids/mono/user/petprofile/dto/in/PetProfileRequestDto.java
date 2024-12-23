package lookids.mono.user.petprofile.dto.in;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.user.petprofile.domain.PetProfile;
import lookids.mono.user.petprofile.vo.in.PetProfileRequestVo;

@Getter
@NoArgsConstructor
public class PetProfileRequestDto {

	private String userUuid;
	private String name;
	private String comment;
	private String image;
	private String gender;
	private Integer age;
	private String type;
	private Float weight;

	@Builder
	public PetProfileRequestDto(String userUuid, String name, String comment, String image, String gender, Integer age,
		String type, Float weight) {
		this.userUuid = userUuid;
		this.name = name;
		this.comment = comment;
		this.image = image;
		this.gender = gender;
		this.age = age;
		this.type = type;
		this.weight = weight;
	}

	public static PetProfileRequestDto toDto(PetProfileRequestVo petProfileRequestVo, String userUuid) {
		return PetProfileRequestDto.builder()
			.userUuid(userUuid)
			.name(petProfileRequestVo.getName())
			.comment(petProfileRequestVo.getComment())
			.image(petProfileRequestVo.getImage())
			.gender(petProfileRequestVo.getGender())
			.age(petProfileRequestVo.getAge())
			.type(petProfileRequestVo.getType())
			.weight(petProfileRequestVo.getWeight())
			.build();
	}

	public PetProfile toEntity() {
		return PetProfile.builder()
			.userUuid(userUuid)
			.petCode(UUID.randomUUID().toString())
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
