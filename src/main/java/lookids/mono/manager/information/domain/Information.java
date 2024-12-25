package lookids.mono.manager.information.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.mono.common.entity.BaseEntity;
import lookids.mono.manager.information.dto.in.InformationUpdateRequestDto;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Information extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String managerUuid;

	@Column(nullable = false)
	private String feedCode;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private List<String> mediaUrls;

	public void update(InformationUpdateRequestDto informationUpdateRequestDto) {
		this.title = informationUpdateRequestDto.getTitle();
		this.content = informationUpdateRequestDto.getContent();
		this.mediaUrls = informationUpdateRequestDto.getMediaUrls();
	}
}
