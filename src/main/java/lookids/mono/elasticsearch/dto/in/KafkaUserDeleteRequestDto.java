package lookids.elasticsearch.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lookids.elasticsearch.domain.SearchUser;

@Getter
@ToString
@NoArgsConstructor
public class KafkaUserDeleteRequestDto {

	private String uuid;

	@Builder
	public KafkaUserDeleteRequestDto(String uuid) {
		this.uuid = uuid;
	}

	public static KafkaUserDeleteRequestDto toDto(SearchUser searchUser) {
		return KafkaUserDeleteRequestDto.builder()
			.uuid(searchUser.getUuid())
			.build();
	}

	public SearchUser toEntity(SearchUser searchUser) {
		return SearchUser.builder()
			.uuid(searchUser.getUuid())
			.nickname(searchUser.getNickname())
			.image(searchUser.getImage())
			.tag(searchUser.getTag())
			.state(false)
			.build();
	}

}
