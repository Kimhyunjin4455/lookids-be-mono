package lookids.user.petprofile.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class PetProfileResponseVo {

	private String petCode;
	private String name;
	private String comment;
	private String gender;
	private Integer age;
	private String type;
	private Float weight;
	private String image;
}
