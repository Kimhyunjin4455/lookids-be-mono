package lookids.mono.feed.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Document
@NoArgsConstructor
public class Feed {

	@Id
	private ObjectId id;
	private String feedCode;
	private String uuid;
	private String content;
	private boolean state;
	private List<String> petCode;
	private List<String> tagList;
	private List<String> mediaUrlList;

	@CreatedDate
	private LocalDateTime createdAt;

	@Builder
	public Feed(ObjectId id, String uuid, String feedCode, String content, List<String> petCode, List<String> tagList,
		boolean state, LocalDateTime createdAt, List<String> mediaUrlList) {
		this.id = id;
		this.feedCode = feedCode;
		this.uuid = uuid;
		this.content = content;
		this.petCode = petCode;
		this.tagList = tagList;
		this.state = state;
		this.createdAt = createdAt;
		this.mediaUrlList = mediaUrlList;
	}
}
