package lookids.mono.elasticsearch.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KafkaPetDeleteRequestDto {

	private String petCode;

	@Builder
	public KafkaPetDeleteRequestDto(String petCode) {
		this.petCode = petCode;
	}

}
