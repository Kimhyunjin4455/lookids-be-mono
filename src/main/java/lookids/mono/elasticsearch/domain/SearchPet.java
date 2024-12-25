package lookids.mono.elasticsearch.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Document(indexName = "v1pet")
public class SearchPet {

	@Id
	private String id;

	@Field(type = FieldType.Text, index = false)
	private String petType;

	@Field(type = FieldType.Text)
	private String petName;

	@Field(type = FieldType.Text, index = false)
	private String petImage;

	@Field(type = FieldType.Keyword)
	private String petCode;

	@Field(type = FieldType.Text, index = false)
	private String userNickname;

	@Field(type = FieldType.Text, index = false)
	private String userTag;

	@Builder
	public SearchPet(String id, String petType, String petName, String petCode, String petImage, String userNickname,
		String userTag) {
		this.id = id;
		this.petName = petName;
		this.petType = petType;
		this.petCode = petCode;
		this.petImage = petImage;
		this.userNickname = userNickname;
		this.userTag = userTag;
	}

}
