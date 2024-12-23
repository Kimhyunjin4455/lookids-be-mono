package lookids.report.report.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lookids.report.report.domain.RequestType;
import lookids.report.report.domain.TargetType;
import lookids.report.report.dto.in.ReportRequestDto;
import lookids.report.report.dto.out.ReportListResponseDto;
import lookids.report.report.dto.out.ReportResponseDto;

public interface ReportService {
	void createReport(ReportRequestDto reportRequestDto);

	Page<ReportListResponseDto> readUserReportList(String uuid, TargetType targetType, RequestType requestType,
		Pageable pageable);

	ReportResponseDto readUserReport(String targetCode);

}
