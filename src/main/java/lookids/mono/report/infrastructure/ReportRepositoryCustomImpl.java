package lookids.report.report.infrastructure;

import java.util.List;

import lookids.report.report.dto.out.ReportListResponseDto;

public class ReportRepositoryCustomImpl implements ReportRepositoryCustom {
	@Override
	public List<ReportListResponseDto> findByUserRead() {
		return List.of();
	}
}
