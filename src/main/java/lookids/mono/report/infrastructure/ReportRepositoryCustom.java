package lookids.report.report.infrastructure;

import java.util.List;

import lookids.report.report.dto.out.ReportListResponseDto;

public interface ReportRepositoryCustom {
	List<ReportListResponseDto> findByUserRead();
}
