package lookids.report.report.infrastructure;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import lookids.report.report.domain.Report;
import lookids.report.report.domain.RequestType;
import lookids.report.report.domain.TargetType;

public interface ReportRepository extends JpaRepository<Report, Long> {
	Page<Report> findByUuidAndTargetTypeAndRequestTypeOrderByCreatedAtDesc(
		String uuid,
		TargetType targetType,
		RequestType requestType,
		Pageable pageable
	);

	Optional<Report> findByTargetCode(String targetCode);
}
