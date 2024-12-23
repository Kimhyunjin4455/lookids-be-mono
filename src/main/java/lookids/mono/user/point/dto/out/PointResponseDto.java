package lookids.user.point.dto.out;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.user.point.domain.Point;
import lookids.user.point.vo.out.PointResponseVo;

@Getter
@NoArgsConstructor
public class PointResponseDto {

	private String userUuid;
	private String eventCode;
	private LocalDateTime agreedAt;
	private Boolean type;
	private Integer value;

	@Builder
	public PointResponseDto(String userUuid, String eventCode, LocalDateTime agreedAt, Boolean type, Integer value) {
		this.userUuid = userUuid;
		this.eventCode = eventCode;
		this.type = type;
		this.value = value;
		this.agreedAt = agreedAt;
	}

	public static PointResponseDto toDto(Point point) {
		return PointResponseDto.builder()
			.userUuid(point.getUserUuid())
			.eventCode(point.getEventCode())
			.type(point.getType())
			.value(point.getValue())
			.agreedAt(point.getAgreedAt())
			.build();
	}

	public PointResponseVo toVo() {
		return PointResponseVo.builder()
			.userUuid(userUuid)
			.eventCode(eventCode)
			.type(type)
			.value(value)
			.agreedAt(agreedAt)
			.build();
	}
}
