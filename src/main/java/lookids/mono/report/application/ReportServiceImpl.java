package lookids.mono.report.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lookids.mono.common.entity.BaseResponseStatus;
import lookids.mono.common.exception.BaseException;
import lookids.mono.report.domain.RequestType;
import lookids.mono.report.domain.TargetType;
import lookids.mono.report.dto.in.ReportRequestDto;
import lookids.mono.report.dto.out.ReportListResponseDto;
import lookids.mono.report.dto.out.ReportResponseDto;
import lookids.mono.report.infrastructure.ReportRepository;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
	private final ReportRepository reportRepository;

	@Override
	public void createReport(ReportRequestDto reportRequestDto) {
		reportRepository.save(reportRequestDto.toEntity());
	}

	@Override
	public Page<ReportListResponseDto> readUserReportList(String uuid, TargetType targetType, RequestType requestType,
		Pageable pageable) {
		return reportRepository.findByUuidAndTargetTypeAndRequestTypeOrderByCreatedAtDesc(uuid, targetType, requestType,
			pageable).map(ReportListResponseDto::toDto);
	}

	@Override
	public ReportResponseDto readUserReport(String targetCode) {
		return ReportResponseDto.toDto(reportRepository.findByTargetCode(targetCode)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NOT_FOUND_REPORT)));
	}

}
