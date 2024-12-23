package lookids.report.report.dto.out;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.report.report.domain.Report;
import lookids.report.report.domain.RequestType;
import lookids.report.report.domain.TargetType;
import lookids.report.report.vo.out.ReportListResponseVo;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReportListResponseDto {
	private String targetCode;
	private TargetType targetType;
	private RequestType requestType;
	private Boolean state;
	private LocalDateTime createdAt;

	public static ReportListResponseDto toDto(Report report) {
		return ReportListResponseDto.builder()
			.targetCode(report.getTargetCode())
			.targetType(report.getTargetType())
			.createdAt(report.getCreatedAt())
			.state(report.getState())
			.requestType(report.getRequestType())
			.build();
	}

	public ReportListResponseVo toVo() {
		return ReportListResponseVo.builder()
			.targetCode(targetCode)
			.targetType(targetType)
			.createdAt(createdAt)
			.state(state)
			.requestType(requestType)
			.build();
	}
}
