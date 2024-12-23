package lookids.user.point.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.user.point.domain.Point;
import lookids.user.point.vo.in.PointRequestVo;

@Getter
@NoArgsConstructor
public class PointRequestDto {

	private String userUuid;
	private String eventCode;
	private Boolean type;
	private Integer value;

	@Builder
	public PointRequestDto(String userUuid, String eventCode, Boolean type, Integer value) {
		this.userUuid = userUuid;
		this.eventCode = eventCode;
		this.value = value;
		this.type = type;
	}

	public static PointRequestDto toDto(PointRequestVo pointRequestVo) {
		return PointRequestDto.builder()
			.userUuid(pointRequestVo.getUserUuid())
			.eventCode(pointRequestVo.getEventCode())
			.value(pointRequestVo.getValue())
			.type(pointRequestVo.getType())
			.build();
	}

	public Point toEntity() {
		return Point.builder().userUuid(userUuid).eventCode(eventCode).value(value).type(type).build();
	}
}
