package lookids.mono.user.petprofile.vo.in;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PetProfileUpdateVo {
	private String petCode;
	private String name;
	private String comment;
	private String gender;
	private Integer age;
	private String type;
	private Float weight;
	private String image;
}
