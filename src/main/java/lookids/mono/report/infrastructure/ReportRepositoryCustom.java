package lookids.mono.report.infrastructure;

import java.util.List;

import lookids.mono.report.dto.out.ReportListResponseDto;

public interface ReportRepositoryCustom {
	List<ReportListResponseDto> findByUserRead();
}
