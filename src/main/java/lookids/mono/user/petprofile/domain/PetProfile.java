package lookids.mono.user.petprofile.domain;

import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@DynamicUpdate  //특정 칼럼만 update하기 위해 사용
@Entity
public class PetProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Comment("유저 uuid")
	@Column(nullable = false)
	private String userUuid;

	@Comment("반려동물 uuid")
	@Column(nullable = false)
	private String petCode;

	@Comment("반려동물 이름")
	@Column(nullable = false)
	private String name;

	@Comment("반려동물 소개")
	@Column(nullable = false)
	private String comment;

	@Comment("반려동물 성별")
	@Column(nullable = true)
	private String gender;

	@Comment("반려동물 나이")
	@Column(nullable = true)
	private Integer age;

	@Comment("반려동물 종")
	@Column(nullable = true)
	private String type;

	@Comment("반려동물 무게")
	@Column(nullable = true)
	private Float weight;

	@Comment("반려동물 이미지")
	@Column(nullable = false)
	private String image;

	@Builder
	public PetProfile(Long id, String userUuid, String petCode, String name, String comment, String gender, Integer age,
		String type, Float weight, String image) {
		this.id = id;
		this.userUuid = userUuid;
		this.petCode = petCode;
		this.name = name;
		this.comment = comment;
		this.gender = gender;
		this.age = age;
		this.type = type;
		this.weight = weight;
		this.image = image;
	}
}
