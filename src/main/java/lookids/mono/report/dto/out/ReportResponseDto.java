package lookids.mono.report.dto.out;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.mono.report.domain.Report;
import lookids.mono.report.domain.RequestType;
import lookids.mono.report.domain.TargetType;
import lookids.mono.report.vo.out.ReportResponseVo;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReportResponseDto {
	private String uuid;
	private RequestType requestType;
	private TargetType targetType;
	private String targetCode;
	private Boolean state;
	private String detail;
	private String reason;
	private LocalDateTime createdAt;

	public static ReportResponseDto toDto(Report report) {
		return ReportResponseDto.builder()
			.uuid(report.getUuid())
			.requestType(report.getRequestType())
			.targetType(report.getTargetType())
			.targetCode(report.getTargetCode())
			.state(report.getState())
			.detail(report.getDetail())
			.reason(report.getReason())
			.createdAt(report.getCreatedAt())
			.build();
	}

	public ReportResponseVo toVo() {
		return ReportResponseVo.builder()
			.uuid(uuid)
			.requestType(requestType)
			.targetType(targetType)
			.targetCode(targetCode)
			.state(state)
			.detail(detail)
			.reason(reason)
			.createdAt(createdAt)
			.build();
	}
}
