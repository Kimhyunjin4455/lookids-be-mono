package lookids.mono.map.dto.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PinReadDto {

	private Double ha; // 좌하단 위도
	private Double qa; // 좌하단 경도
	private Double pa; // 우상단 위도
	private Double oa; // 우상단 경도

	@Builder
	public PinReadDto(Double ha, Double qa, Double pa, Double oa) {
		this.ha = ha;
		this.qa = qa;
		this.oa = oa;
		this.pa = pa;
	}

	public static PinReadDto toDto(Double ha, Double oa, Double pa, Double qa) {
		return PinReadDto.builder().ha(ha).oa(oa).pa(pa).qa(qa).build();

	}

}
