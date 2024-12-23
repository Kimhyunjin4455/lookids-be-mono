package lookids.map.map.vo.in;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PinReadVo {

	private Double ha; // 좌하단 위도
	private Double oa; // 촤하단 경도
	private Double pa; // 우상단 위도
	private Double qa; // 우상단 경도

}
