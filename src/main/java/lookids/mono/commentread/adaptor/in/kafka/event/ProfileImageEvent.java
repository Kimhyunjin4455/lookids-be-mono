package lookids.mono.commentread.adaptor.in.kafka.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ProfileImageEvent {
	private String uuid;
	private String image;
}
