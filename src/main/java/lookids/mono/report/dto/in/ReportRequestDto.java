package lookids.mono.report.dto.in;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.mono.report.domain.Report;
import lookids.mono.report.domain.RequestType;
import lookids.mono.report.domain.TargetType;
import lookids.mono.report.vo.in.UserReportRequestVo;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReportRequestDto {
	private String uuid;
	private String targetCode;
	private TargetType targetType;
	private String detail;
	private String reason;
	private LocalDateTime createdAt;
	private Boolean state;
	private RequestType requestType;

	public static ReportRequestDto toDto(UserReportRequestVo userReportRequestVo, String uuid) {
		return ReportRequestDto.builder()
			.uuid(uuid)
			.targetCode(userReportRequestVo.getTargetCode())
			.targetType(userReportRequestVo.getTargetType())
			.detail(userReportRequestVo.getDetail())
			.reason(userReportRequestVo.getReason())
			.createdAt(LocalDateTime.now())
			.state(false)
			.requestType(userReportRequestVo.getRequestType())
			.build();
	}

	public Report toEntity() {
		return Report.builder()
			.uuid(uuid)
			.targetCode(targetCode)
			.targetType(targetType)
			.detail(detail)
			.reason(reason)
			.createdAt(createdAt)
			.state(state)
			.requestType(requestType)
			.build();
	}

}
