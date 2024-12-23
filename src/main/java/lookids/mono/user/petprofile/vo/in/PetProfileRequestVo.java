package lookids.mono.user.petprofile.vo.in;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PetProfileRequestVo {
	private String name;
	private String comment;
	private String image;
	private String gender;
	private Integer age;
	private String type;
	private Float weight;
}
