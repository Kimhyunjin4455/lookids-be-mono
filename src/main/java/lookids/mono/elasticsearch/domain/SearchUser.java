package lookids.elasticsearch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Document(indexName = "member")
public class SearchUser {

	@Id
	private String id;

	@Field(type = FieldType.Keyword)
	private String uuid;

	@Field(type = FieldType.Text)
	private String nickname;

	@Field(type = FieldType.Text)
	private String tag;

	@Field(type = FieldType.Keyword, index = false)
	private String image;

	@Field(type = FieldType.Boolean)
	private boolean state;


	@Builder
	public SearchUser(String id, String uuid, String nickname, String tag, String image, boolean state) {
		this.id = id;
		this.uuid = uuid;
		this.nickname = nickname;
		this.tag = tag;
		this.image = image;
		this.state = state;
	}

}
