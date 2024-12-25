package lookids.mono.elasticsearch.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.mono.elasticsearch.domain.SearchUser;

@Getter
@ToString
@NoArgsConstructor
public class KafkaUserImageUpdateRequestDto {

	private String uuid;
	private String image;

	@Builder
	public KafkaUserImageUpdateRequestDto(String uuid, String nickname, String tag, String image, boolean state) {
		this.uuid = uuid;
		this.image = image;
	}

	public static SearchUser toUpdateImage(SearchUser searchUser,
		KafkaUserImageUpdateRequestDto kafkaUserImageUpdateRequestDto) {
		return SearchUser.builder()
			.id(searchUser.getId())
			.uuid(searchUser.getUuid())
			.nickname(searchUser.getNickname())
			.tag(searchUser.getTag())
			.image(kafkaUserImageUpdateRequestDto.getImage())
			.state(true)
			.build();
	}

}
