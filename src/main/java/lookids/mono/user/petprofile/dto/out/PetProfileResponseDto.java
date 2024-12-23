package lookids.user.petprofile.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.user.petprofile.domain.PetProfile;
import lookids.user.petprofile.vo.out.PetProfileDeleteKafkaVo;
import lookids.user.petprofile.vo.out.PetProfileKafkaVo;
import lookids.user.petprofile.vo.out.PetProfileResponseVo;
import lookids.user.petprofile.vo.out.PetProfileSearchKafkaVo;
import lookids.user.userprofile.dto.out.UserProfileResponseDto;

@Getter
@NoArgsConstructor
public class PetProfileResponseDto {

	private String userUuid;
	private String petCode;
	private String name;
	private String comment;
	private String gender;
	private Integer age;
	private String type;
	private Float weight;
	private String image;

	@Builder
	public PetProfileResponseDto(String userUuid, String petCode, String name, String comment, String gender,
		Integer age, String type, Float weight, String image) {
		this.userUuid = userUuid;
		this.petCode = petCode;
		this.name = name;
		this.comment = comment;
		this.gender = gender;
		this.age = age;
		this.type = type;
		this.weight = weight;
		this.image = image;
	}

	public static PetProfileResponseDto toDto(PetProfile petProfile) {
		return PetProfileResponseDto.builder()
			.userUuid(petProfile.getUserUuid())
			.petCode(petProfile.getPetCode())
			.name(petProfile.getName())
			.comment(petProfile.getComment())
			.gender(petProfile.getGender())
			.age(petProfile.getAge())
			.type(petProfile.getType())
			.weight(petProfile.getWeight())
			.image(petProfile.getImage())
			.build();
	}

	public PetProfileResponseVo toVo() {
		return PetProfileResponseVo.builder()
			.petCode(petCode)
			.name(name)
			.comment(comment)
			.age(age)
			.gender(gender)
			.type(type)
			.weight(weight)
			.image(image)
			.build();
	}

	public PetProfileKafkaVo toKafkaVo() {
		return PetProfileKafkaVo.builder().petCode(petCode).image(image).petName(name).petType(type).build();
	}

	public PetProfileSearchKafkaVo toSearchVo(UserProfileResponseDto userProfileResponseDto) {
		return PetProfileSearchKafkaVo.builder()
			.petCode(petCode)
			.petName(name)
			.petImage(image)
			.petType(type)
			.userNickname(userProfileResponseDto.getNickname())
			.userTag(userProfileResponseDto.getTag())
			.build();
	}

	public PetProfileDeleteKafkaVo toDeleteVo() {
		return PetProfileDeleteKafkaVo.builder().petCode(petCode).build();
	}
}
