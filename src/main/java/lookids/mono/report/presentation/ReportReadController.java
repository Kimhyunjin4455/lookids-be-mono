package lookids.report.report.presentation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lookids.report.common.entity.BaseResponse;
import lookids.report.report.application.ReportService;
import lookids.report.report.domain.RequestType;
import lookids.report.report.domain.TargetType;
import lookids.report.report.dto.out.ReportListResponseDto;
import lookids.report.report.vo.out.ReportListResponseVo;
import lookids.report.report.vo.out.ReportResponseVo;

@RequiredArgsConstructor
@RequestMapping("/read/report")
@RestController
public class ReportReadController {

	private final ReportService reportService;

	@Operation(summary = "Read Report/Inquiry API", description = "자신의 신고/문의 리스트를 볼 수 있는 Read Report/Inquiry API 입니다", tags = {
		"Report/Inquiry"})
	@GetMapping("/report-list")
	public BaseResponse<Page<ReportListResponseVo>> readUserReportList(
		@RequestHeader String uuid,
		@RequestParam RequestType requestType,
		@RequestParam TargetType targetType,
		@RequestParam(defaultValue = "0") int page
	) {
		Pageable pageable = PageRequest.of(page, 20);
		return new BaseResponse<>(
			reportService.readUserReportList(uuid, targetType, requestType, pageable)
				.map(ReportListResponseDto::toVo)
		);
	}

	@Operation(summary = "Read Report/Inquiry API", description = "자신의 신고/문의를 볼 수 있는 Read Report/Inquiry API 입니다", tags = {
		"Report/Inquiry"})
	@GetMapping("")
	public BaseResponse<ReportResponseVo> readUserReport(
		@RequestParam String targetCode
	) {
		return new BaseResponse<>(reportService.readUserReport(targetCode).toVo());
	}
}
