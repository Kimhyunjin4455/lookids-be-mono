package lookids.mono.notification.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.notification.vo.in.FcmTokenRequestVo;

@Getter
@NoArgsConstructor
public class FcmTokenRequestDto {
	private String uuid;
	private String fcmToken;

	@Builder
	public FcmTokenRequestDto(String uuid, String fcmToken) {
		this.uuid = uuid;
		this.fcmToken = fcmToken;
	}

	public static FcmTokenRequestDto toDto(String uuid, FcmTokenRequestVo fcmTokenRequestVo) {

		return FcmTokenRequestDto.builder()
			.uuid(uuid)
			.fcmToken(fcmTokenRequestVo.getFcmToken()) // 존재한다면 리스트에 fcm 추가, 없다면 리스트 생성 후 등록
			.build();
	}

}
