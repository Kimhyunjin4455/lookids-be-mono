package lookids.user.point.application;

import java.util.List;

import lookids.user.point.dto.in.PointRequestDto;
import lookids.user.point.dto.out.PointResponseDto;

public interface PointService {
	void createPoint(PointRequestDto pointRequestDto);

	void deletePoint(Long id);

	List<PointResponseDto> readPointType(String userUuid, Boolean type);

	List<PointResponseDto> readPointList(String userUuid);
}
