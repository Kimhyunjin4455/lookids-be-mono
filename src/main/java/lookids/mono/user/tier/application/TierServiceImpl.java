package lookids.user.tier.application;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.user.common.entity.BaseResponseStatus;
import lookids.user.common.exception.BaseException;
import lookids.user.tier.domain.Tier;
import lookids.user.tier.dto.in.TierRequestDto;
import lookids.user.tier.dto.out.TierResponseDto;
import lookids.user.tier.infrastructure.TierRepository;

@Service
@AllArgsConstructor
@Slf4j
public class TierServiceImpl implements TierService {

	private final TierRepository tierRepository;

	@Override
	public void createTier(TierRequestDto tierRequestDto) {
		tierRepository.save(tierRequestDto.toEntity());
	}

	@Override
	public void updateTier(TierRequestDto tierRequestDto) {
		Tier tier = tierRepository.findByTierCode(tierRequestDto.getTierCode())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		tierRepository.save(tier);
	}

	@Override
	public void deleteTier(String tierCode) {
		Tier tier = tierRepository.findByTierCode(tierCode)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));
		tierRepository.deleteById(tier.getId());
	}

	@Override
	public TierResponseDto readTier(String tierCode) {
		return TierResponseDto.toDto(tierRepository.findByTierCode(tierCode)
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA)));
	}

	@Override
	public List<TierResponseDto> readTierList() {
		List<Tier> tierList = tierRepository.findAll();
		return tierList.stream().map(TierResponseDto::toDto).toList();
	}
}
