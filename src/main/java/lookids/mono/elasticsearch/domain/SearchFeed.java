package lookids.elasticsearch.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Document(indexName = "v1feed")
public class SearchFeed {

	@Id
	private String id;

	@Field(type = FieldType.Keyword)
	private String feedCode;

	@Field(type = FieldType.Text)
	private List<String> petCode;

	@Field(type = FieldType.Text)
	private List<String> tagList;

	@Field(type = FieldType.Text, index = false)
	private List<String> mediaUrlList;

	@Builder
	public SearchFeed(String id, String feedCode, List<String> petCode, List<String> tagList, List<String> mediaUrlList) {
		this.id = id;
		this.feedCode = feedCode;
		this.petCode = petCode;
		this.tagList = tagList;
		this.mediaUrlList = mediaUrlList;
	}

}
