package lookids.user.petprofile.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class PetProfileDeleteKafkaVo {
	private String petCode;
}
