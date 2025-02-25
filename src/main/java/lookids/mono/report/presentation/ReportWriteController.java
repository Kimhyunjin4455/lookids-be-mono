package lookids.mono.report.presentation;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lookids.mono.common.entity.BaseResponse;
import lookids.mono.report.application.ReportService;
import lookids.mono.report.dto.in.ReportRequestDto;
import lookids.mono.report.vo.in.UserReportRequestVo;

@RequiredArgsConstructor
@RequestMapping("/report-service/write/report")
@RestController
public class ReportWriteController {

	private final ReportService reportService;

	@Operation(summary = "Create Report/Inquiry API", description = "Create Report/Inquiry API 입니다", tags = {
		"Report/Inquiry"})
	@PostMapping
	public BaseResponse<Void> createReport(@RequestHeader String uuid,
		@RequestBody UserReportRequestVo userReportRequestVo) {
		reportService.createReport(ReportRequestDto.toDto(userReportRequestVo, uuid));
		return new BaseResponse<>();
	}
}
