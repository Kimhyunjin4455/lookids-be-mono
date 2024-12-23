package lookids.mono.user.tier.application;

import java.util.List;

import lookids.mono.user.tier.dto.in.TierRequestDto;
import lookids.mono.user.tier.dto.out.TierResponseDto;

public interface TierService {
	void createTier(TierRequestDto tierRequestDto);

	void updateTier(TierRequestDto tierRequestDto);

	void deleteTier(String tierCode);

	TierResponseDto readTier(String tierCode);

	List<TierResponseDto> readTierList();

}
