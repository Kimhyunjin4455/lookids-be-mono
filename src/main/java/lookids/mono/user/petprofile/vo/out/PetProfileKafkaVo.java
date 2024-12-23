package lookids.user.petprofile.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class PetProfileKafkaVo {
	private String petCode;
	private String image;
	private String petName;
	private String petType;
}
