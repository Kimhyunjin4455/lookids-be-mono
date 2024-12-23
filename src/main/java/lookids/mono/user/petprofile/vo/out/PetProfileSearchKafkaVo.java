package lookids.mono.user.petprofile.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class PetProfileSearchKafkaVo {
	private String petCode;
	private String petName;
	private String petType;
	private String petImage;
	private String userNickname;
	private String userTag;
}
