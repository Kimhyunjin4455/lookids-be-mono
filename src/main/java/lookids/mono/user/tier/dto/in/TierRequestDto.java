package lookids.mono.user.tier.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.user.tier.domain.Tier;
import lookids.mono.user.tier.vo.in.TierRequestVo;

@Getter
@NoArgsConstructor
public class TierRequestDto {

	private String tierCode;
	private String name;
	private Integer amount;
	private String icon;

	@Builder
	public TierRequestDto(String tierCode, String name, Integer amount, String icon) {
		this.tierCode = tierCode;
		this.name = name;
		this.amount = amount;
		this.icon = icon;
	}

	public static TierRequestDto toDto(TierRequestVo tierRequestVo) {
		return TierRequestDto.builder()
			.tierCode(tierRequestVo.getTierCode())
			.name(tierRequestVo.getName())
			.amount(tierRequestVo.getAmount())
			.icon(tierRequestVo.getIcon())
			.build();
	}

	public Tier toEntity() {
		return Tier.builder().tierCode(tierCode).name(name).amount(amount).icon(icon).build();
	}
}
