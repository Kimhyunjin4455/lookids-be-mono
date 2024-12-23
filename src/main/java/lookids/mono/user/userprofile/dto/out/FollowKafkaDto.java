package lookids.mono.user.userprofile.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.user.userprofile.domain.UserProfile;
import lookids.mono.user.userprofile.vo.out.FollowKafkaVo;

@Getter
@NoArgsConstructor
public class FollowKafkaDto {
	private String senderUuid;
	private String senderNickname;
	private String senderTag;
	private String senderImage;

	private String receiverUuid;
	private String receiverNickname;
	private String receiverTag;
	private String receiverImage;

	@Builder
	public FollowKafkaDto(String senderUuid, String senderNickname, String senderTag, String senderImage,
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

	public static FollowKafkaDto toDto(UserProfile senderProfile, UserProfile receiverProfile) {
		return FollowKafkaDto.builder()
			.senderUuid(senderProfile.getUserUuid())
			.senderNickname(senderProfile.getNickname())
			.senderTag(senderProfile.getTag())
			.senderImage(senderProfile.getImage())
			.receiverUuid(receiverProfile.getUserUuid())
			.receiverNickname(receiverProfile.getNickname())
			.receiverTag(receiverProfile.getTag())
			.receiverImage(receiverProfile.getImage())
			.build();
	}

	public FollowKafkaVo toVo() {
		return FollowKafkaVo.builder()
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
