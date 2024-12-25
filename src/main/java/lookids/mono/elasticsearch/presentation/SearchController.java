package lookids.mono.elasticsearch.presentation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lookids.mono.common.entity.BaseResponse;
import lookids.mono.elasticsearch.application.SearchService;
import lookids.mono.elasticsearch.dto.out.SearchFeedResponseDto;
import lookids.mono.elasticsearch.dto.out.SearchPetResponseDto;
import lookids.mono.elasticsearch.dto.out.SearchUserResponseDto;
import lookids.mono.elasticsearch.vo.SearchFeedResponseVo;
import lookids.mono.elasticsearch.vo.SearchPetResponseVo;
import lookids.mono.elasticsearch.vo.SearchUserResponseVo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search-service/read/search/")
public class SearchController {

	private final SearchService searchService;

	@Operation(summary = "searchFeedByTag API", description = "searchFeedByTag API 태그로 피드를 검색, 태그는 정확한 값을 입력")
	@GetMapping("/feed")
	public BaseResponse<Page<SearchFeedResponseVo>> searchFeedByTag(@RequestParam(defaultValue = "") String searchParam,
		@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<SearchFeedResponseDto> searchFeedResponseDtoList = searchService.searchFeedByTag(searchParam, pageable);

		return new BaseResponse<>(searchFeedResponseDtoList.map(SearchFeedResponseDto::toVo));
	}

	@Operation(summary = "searchFeedByPetCode API", description = "searchFeedByPetCode API 펫코드로 피드를 검색, 펫 코드는 정확한 값을 입력")
	@GetMapping("/feedPetCode")
	public BaseResponse<Page<SearchFeedResponseVo>> searchFeedByPetCode(
		@RequestParam(defaultValue = "") String searchParam, @RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<SearchFeedResponseDto> searchFeedResponseDtoList = searchService.searchFeedByPetCode(searchParam,
			pageable);

		return new BaseResponse<>(searchFeedResponseDtoList.map(SearchFeedResponseDto::toVo));
	}

	@Operation(summary = "searchUser API", description = "searchUser API 닉네임 또는 닉네임@태그로 검색")
	@GetMapping("/user")
	public BaseResponse<Page<SearchUserResponseVo>> searchUser(@RequestParam(defaultValue = "") String searchParam,
		@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<SearchUserResponseDto> searchUserResponseDtoList = searchService.searchUser(searchParam, pageable);

		return new BaseResponse<>(searchUserResponseDtoList.map(SearchUserResponseDto::toVo));
	}

	@Operation(summary = "searchPet API", description = "searchPet API 반려동물 이름으로 검색")
	@GetMapping("/pet")
	public BaseResponse<Page<SearchPetResponseVo>> searchPet(@RequestParam(defaultValue = "") String searchParam,
		@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<SearchPetResponseDto> searchPetResponseDtoList = searchService.searchPet(searchParam, pageable);

		return new BaseResponse<>(searchPetResponseDtoList.map(SearchPetResponseDto::toVo));
	}

}
