package lookids.mono.followblock.follow.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.followblock.follow.domain.FollowInfo;
import lookids.mono.followblock.follow.vo.out.FollowUserProfileResponseVo;

@Getter
@NoArgsConstructor
public class KafkaFollowDto {
	private String senderUuid;
	private String senderNickname;
	private String senderTag;
	private String senderImage;
	private String receiverUuid;
	private String receiverNickname;
	private String receiverTag;
	private String receiverImage;

	@Builder
	public KafkaFollowDto(String senderUuid, String senderNickname, String senderTag, String senderImage,
		String receiverUuid, String receiverNickname, String receiverTag, String receiverImage) {
		this.senderUuid = senderUuid;
		this.senderNickname = senderNickname;
		this.senderTag = senderTag;
		this.senderImage = senderImage;
		this.receiverUuid = receiverUuid;
		this.receiverNickname = receiverNickname;
		this.receiverTag = receiverTag;
		this.receiverImage = receiverImage;
	}

	public static FollowUserProfileResponseVo toVo(FollowInfo followInfo) {
		return FollowUserProfileResponseVo.builder()
			.uuid(followInfo.getReceiverUuid())
			.nickname(followInfo.getReceiverNickname())
			.tag(followInfo.getReceiverTag())
			.image(followInfo.getReceiverImage())
			.build();
	}

	public FollowInfo toEntity() {
		return FollowInfo.builder()
			.senderUuid(senderUuid)
			.senderNickname(senderNickname)
			.senderTag(senderTag)
			.senderImage(senderImage)
			.receiverUuid(receiverUuid)
			.receiverNickname(receiverNickname)
			.receiverTag(receiverTag)
			.receiverImage(receiverImage)
			.build();
	}
}
