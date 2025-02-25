package lookids.mono.report.vo.out;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.mono.report.domain.RequestType;
import lookids.mono.report.domain.TargetType;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
public class ReportListResponseVo {
	private String targetCode;
	private TargetType targetType;
	private RequestType requestType;
	private Boolean state;
	private LocalDateTime createdAt;
}
