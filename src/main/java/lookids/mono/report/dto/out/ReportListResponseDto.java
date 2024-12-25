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
import lookids.mono.report.vo.out.ReportListResponseVo;

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
