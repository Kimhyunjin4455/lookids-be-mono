package Lookids.follow.dto.in;

import Lookids.follow.domain.FollowInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class KafkaUserUpdateRequestDto {

	private Long id;
	private String uuid;
	private String nickname;
	private String tag;
	private String image;
	private boolean state;

	@Builder
	public KafkaUserUpdateRequestDto(Long id, String uuid, String nickname, String tag, String image, boolean state) {
		this.id = id;
		this.uuid = uuid;
		this.nickname = nickname;
		this.tag = tag;
		this.image = image;
		this.state = state;
	}

	public static KafkaUserUpdateRequestDto toUpdateSenderNickname(FollowInfo followInfo,
		KafkaUserUpdateRequestDto kafkaUserUpdateRequestDto) {
		return KafkaUserUpdateRequestDto.builder()
			.id(followInfo.getId())
			.uuid(followInfo.getSenderUuid())
			.nickname(kafkaUserUpdateRequestDto.getNickname())
			.tag(kafkaUserUpdateRequestDto.getTag())
			.image(followInfo.getSenderImage())
			.state(true)
			.build();
	}

	public static KafkaUserUpdateRequestDto toUpdateReceiverNickname(FollowInfo followInfo,
		KafkaUserUpdateRequestDto kafkaUserUpdateRequestDto) {
		return KafkaUserUpdateRequestDto.builder()
			.id(followInfo.getId())
			.uuid(followInfo.getReceiverUuid())
			.nickname(kafkaUserUpdateRequestDto.getNickname())
			.tag(kafkaUserUpdateRequestDto.getTag())
			.image(followInfo.getReceiverNickname())
			.state(true)
			.build();
	}

	public static KafkaUserUpdateRequestDto toUpdateSenderImage(FollowInfo followInfo,
		KafkaUserUpdateRequestDto kafkaUserUpdateRequestDto) {
		return KafkaUserUpdateRequestDto.builder()
			.id(followInfo.getId())
			.uuid(followInfo.getSenderUuid())
			.nickname(followInfo.getSenderNickname())
			.tag(followInfo.getSenderTag())
			.image(kafkaUserUpdateRequestDto.getImage())
			.state(true)
			.build();
	}

	public static KafkaUserUpdateRequestDto toUpdateReceiverImage(FollowInfo followInfo,
		KafkaUserUpdateRequestDto kafkaUserUpdateRequestDto) {
		return KafkaUserUpdateRequestDto.builder()
			.id(followInfo.getId())
			.uuid(followInfo.getReceiverUuid())
			.nickname(followInfo.getReceiverNickname())
			.tag(followInfo.getReceiverTag())
			.image(kafkaUserUpdateRequestDto.getImage())
			.state(true)
			.build();
	}

	public FollowInfo toEntity() {
		return FollowInfo.builder()
			.receiverImage(image)
			.receiverNickname(nickname)
			.receiverTag(tag)
			.senderImage(image)
			.senderNickname(nickname)
			.senderTag(tag)
			.build();
	}

}
