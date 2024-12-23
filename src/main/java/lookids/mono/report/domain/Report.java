package lookids.report.report.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String uuid;

	@Column(nullable = false)
	private String targetCode;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TargetType targetType;

	@Column(nullable = false)
	private String detail;

	@Column(nullable = false)
	private String reason;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	@ColumnDefault("false")
	private Boolean state;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private RequestType requestType;
}
