package lookids.report.report.vo.in;

import lombok.Getter;
import lombok.ToString;
import lookids.report.report.domain.RequestType;
import lookids.report.report.domain.TargetType;

@ToString
@Getter
public class UserReportRequestVo {
	private String targetCode;
	private TargetType targetType;
	private RequestType requestType;
	private String detail;
	private String reason;
}
