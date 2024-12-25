package lookids.mono.elasticsearch.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lookids.mono.elasticsearch.dto.out.SearchFeedResponseDto;
import lookids.mono.elasticsearch.dto.out.SearchPetResponseDto;
import lookids.mono.elasticsearch.dto.out.SearchUserResponseDto;

public interface SearchService {

	Page<SearchUserResponseDto> searchUser(String searchUser, Pageable pageable);

	Page<SearchFeedResponseDto> searchFeedByTag(String searchFeed, Pageable pageable);

	Page<SearchFeedResponseDto> searchFeedByPetCode(String searchFeed, Pageable pageable);

	Page<SearchPetResponseDto> searchPet(String searchFeed, Pageable pageable);

}
