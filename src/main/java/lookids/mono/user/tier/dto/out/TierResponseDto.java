package lookids.mono.user.tier.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.user.tier.domain.Tier;
import lookids.mono.user.tier.vo.out.TierResponseVo;

@Getter
@NoArgsConstructor
public class TierResponseDto {

	private String tierCode;
	private String name;
	private Integer amount;
	private String icon;

	@Builder
	public TierResponseDto(String tierCode, String name, Integer amount, String icon) {
		this.tierCode = tierCode;
		this.name = name;
		this.amount = amount;
		this.icon = icon;
	}

	public static TierResponseDto toDto(Tier tier) {
		return TierResponseDto.builder()
			.tierCode(tier.getTierCode())
			.name(tier.getName())
			.amount(tier.getAmount())
			.icon(tier.getIcon())
			.build();
	}

	public TierResponseVo toVo() {
		return TierResponseVo.builder().tierCode(tierCode).name(name).amount(amount).icon(icon).build();
	}
}
