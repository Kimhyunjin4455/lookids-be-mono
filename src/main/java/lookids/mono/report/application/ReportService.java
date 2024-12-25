package lookids.mono.report.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lookids.mono.report.domain.RequestType;
import lookids.mono.report.domain.TargetType;
import lookids.mono.report.dto.in.ReportRequestDto;
import lookids.mono.report.dto.out.ReportListResponseDto;
import lookids.mono.report.dto.out.ReportResponseDto;

public interface ReportService {
	void createReport(ReportRequestDto reportRequestDto);

	Page<ReportListResponseDto> readUserReportList(String uuid, TargetType targetType, RequestType requestType,
		Pageable pageable);

	ReportResponseDto readUserReport(String targetCode);

}
