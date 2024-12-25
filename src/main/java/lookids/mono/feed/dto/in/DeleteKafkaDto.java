package lookids.mono.feed.dto.in;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class DeleteKafkaDto {
	private String feedCode;
	private String uuid;
	private LocalDateTime createdAt;

	@Builder
	public DeleteKafkaDto(String feedCode, String uuid, LocalDateTime createdAt) {
		this.feedCode = feedCode;
		this.uuid = uuid;
		this.createdAt = createdAt;
	}

	public static DeleteKafkaDto toDto(String feedCode, String uuid) {
		return DeleteKafkaDto.builder().feedCode(feedCode).uuid(uuid).createdAt(LocalDateTime.now()).build();
	}
}
