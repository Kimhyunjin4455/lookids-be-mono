package lookids.mono.elasticsearch.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.mono.elasticsearch.domain.SearchUser;

@Getter
@ToString
@NoArgsConstructor
public class KafkaUserNicknameUpdateRequestDto {

	private String uuid;
	private String nickname;
	private String tag;

	@Builder
	public KafkaUserNicknameUpdateRequestDto(String uuid, String nickname, String tag) {
		this.uuid = uuid;
		this.nickname = nickname;
		this.tag = tag;
	}

	public static SearchUser toUpdateNickname(SearchUser searchUser,
		KafkaUserNicknameUpdateRequestDto kafkaUserNicknameUpdateRequestDto) {
		return SearchUser.builder()
			.id(searchUser.getId())
			.uuid(searchUser.getUuid())
			.nickname(kafkaUserNicknameUpdateRequestDto.getNickname())
			.tag(kafkaUserNicknameUpdateRequestDto.getTag())
			.image(searchUser.getImage())
			.state(true)
			.build();
	}

}
