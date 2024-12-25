package lookids.mono.report.infrastructure;

import java.util.List;

import lookids.mono.report.dto.out.ReportListResponseDto;

public class ReportRepositoryCustomImpl implements ReportRepositoryCustom {
	@Override
	public List<ReportListResponseDto> findByUserRead() {
		return List.of();
	}
}
