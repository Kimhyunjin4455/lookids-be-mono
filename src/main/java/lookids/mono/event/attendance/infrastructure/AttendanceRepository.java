package lookids.mono.event.attendance.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lookids.mono.event.attendance.domain.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
	Optional<Attendance> findByUuidAndEventCode(String uuid, String eventCode);
}
