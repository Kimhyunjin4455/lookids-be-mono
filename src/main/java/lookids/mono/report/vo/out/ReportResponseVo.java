package lookids.report.report.vo.out;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.report.report.domain.RequestType;
import lookids.report.report.domain.TargetType;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReportResponseVo {
	private String uuid;
	private RequestType requestType;
	private TargetType targetType;
	private String targetCode;
	private Boolean state;
	private String detail;
	private String reason;
	private LocalDateTime createdAt;
}
